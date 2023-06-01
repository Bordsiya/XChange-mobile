package com.example.xchange.utils.adapters

import android.app.Dialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.xchange.R
import com.example.xchange.api_clients.CustomerClient
import com.example.xchange.model.BuyProductsRequest
import com.example.xchange.model.NotificationSubscriptionRequest
import com.example.xchange.model.Product
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection


class ProductAdapter(private val data: List<Product>, private val customerClient: CustomerClient, private val fragmentActivity: FragmentActivity) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(val view: View, val customerClient: CustomerClient, val fragmentActivity: FragmentActivity): RecyclerView.ViewHolder(view){

        fun bind(product: Product){

            val stuffType = view.findViewById<TextView>(R.id.tv_stuff_type)
            val modelId = view.findViewById<TextView>(R.id.tv_model_id)
            val title = view.findViewById<TextView>(R.id.tv_title)

            val linearLayoutFlags = view.findViewById<LinearLayout>(R.id.ll_card_flags)
            linearLayoutFlags.removeAllViews()

            val linearLayoutPrices = view.findViewById<LinearLayout>(R.id.ll_card_prices)
            linearLayoutPrices.removeAllViews()

            val linearLayoutDashes = view.findViewById<LinearLayout>(R.id.ll_card_dashes)
            linearLayoutDashes.removeAllViews()

            val linearLayoutSuppliers = view.findViewById<LinearLayout>(R.id.ll_card_suppliers)
            linearLayoutSuppliers.removeAllViews()

            stuffType.text = product.stuffType
            modelId.text = product.modelId
            title.text = product.title
            for (supplierPrice in product.supplierPrices) {
                val flagImageView = LayoutInflater.from(view.context).inflate(R.layout.customer_search_card_flag, null) as ImageView
                val priceTextView = LayoutInflater.from(view.context).inflate(R.layout.customer_search_card_price, null) as TextView
                val dashTextView = LayoutInflater.from(view.context).inflate(R.layout.customer_search_card_dash, null) as TextView
                val supplierTextView = LayoutInflater.from(view.context).inflate(R.layout.customer_search_card_supplier, null) as TextView
                val subscribeButton = view.findViewById<AppCompatButton>(R.id.btn_subscribe_product)

                val price = supplierPrice.price.amount + supplierPrice.price.currency
                priceTextView.text = price

                val supplier = "@" + supplierPrice.supplierId
                supplierTextView.text = supplier

                supplierTextView.setOnClickListener {
                    val dialog = LayoutInflater.from(view.context).inflate(R.layout.customer_search_dialog_for_buy_product, null) as Dialog
                    val dialogModel = dialog.findViewById<TextView>(R.id.tv_alert_model)
                    val dialogSupplierId = dialog.findViewById<TextView>(R.id.tv_alert_supplier_id)
                    val dialogAmountEt = dialog.findViewById<TextInputEditText>(R.id.te_alert_amount_of_products)
                    val dialogBtnBack = dialog.findViewById<Button>(R.id.dialog_button_back)
                    val dialogBtnSend = dialog.findViewById<Button>(R.id.dialog_button_send)

                    dialogModel.text = product.modelId
                    dialogSupplierId.text = supplier

                    dialogBtnBack.setOnClickListener {
                        dialog.dismiss()
                    }

                    dialogBtnSend.setOnClickListener {
                        if (dialogAmountEt.text.toString().isBlank()
                            || dialogAmountEt.text.toString().toLong() == 0L) {
                            Toast.makeText(fragmentActivity,
                                "Количество товаров должно быть заполненым и больше 0",
                                Toast.LENGTH_SHORT).show()
                        }
                        else {
                            customerClient.getCustomerAPI(fragmentActivity).buyProducts(
                                BuyProductsRequest(
                                    supplierPrice.supplierId,
                                    product.stuffType,
                                    product.modelId,
                                    dialogAmountEt.text.toString().toLong()
                                )
                            ).enqueue(object : Callback<Void> {

                                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                                    if (response.code() == HttpURLConnection.HTTP_OK) dialog.dismiss()
                                    else {
                                        Toast.makeText(fragmentActivity,
                                            "Ошибка во время покупки",
                                            Toast.LENGTH_SHORT).show()
                                    }
                                }

                                override fun onFailure(call: Call<Void>, t: Throwable) {
                                    Toast.makeText(fragmentActivity,
                                        t.message,
                                        Toast.LENGTH_SHORT).show()
                                }

                            })
                        }
                    }
                    dialog.show()
                }
                subscribeButton.setOnClickListener {
                    var action = "SUBSCRIBE"
                    if (subscribeButton.text != "Следить за ценой") action = "UNSUBSCRIBE"
                    customerClient.getCustomerAPI(fragmentActivity).changeNotificationSubscription(
                        NotificationSubscriptionRequest(
                            action,
                            product.modelId
                        )
                    ).enqueue(object : Callback<Void> {
                        override fun onResponse(call: Call<Void>, response: Response<Void>) {
                            if (response.code() == HttpURLConnection.HTTP_OK) subscribeButton.text = "Отписаться"
                            else {
                                Toast.makeText(fragmentActivity,
                                    "Ошибка во время отписки",
                                    Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onFailure(call: Call<Void>, t: Throwable) {
                            Toast.makeText(fragmentActivity,
                                t.message,
                                Toast.LENGTH_SHORT).show()
                        }
                    })

                }

                linearLayoutFlags.addView(flagImageView)
                linearLayoutPrices.addView(priceTextView)
                linearLayoutDashes.addView(dashTextView)
                linearLayoutSuppliers.addView(supplierTextView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.customer_search_card, parent, false)
        return ProductViewHolder(v, customerClient, fragmentActivity)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(data[position])
    }
}