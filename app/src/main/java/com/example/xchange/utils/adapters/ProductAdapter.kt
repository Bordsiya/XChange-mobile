package com.example.xchange.utils.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.xchange.R
import com.example.xchange.model.Product


class ProductAdapter(private val data: List<Product>) : RecyclerView.Adapter<ProductAdapter.MyViewHolder>() {

    class MyViewHolder(val view: View): RecyclerView.ViewHolder(view){

        fun bind(product: Product){

            val title = view.findViewById<TextView>(R.id.tv_title)

            val linearLayoutFlags = view.findViewById<LinearLayout>(R.id.ll_card_flags)
            linearLayoutFlags.removeAllViews()

            val linearLayoutPrices = view.findViewById<LinearLayout>(R.id.ll_card_prices)
            linearLayoutPrices.removeAllViews()

            val linearLayoutDashes = view.findViewById<LinearLayout>(R.id.ll_card_dashes)
            linearLayoutDashes.removeAllViews()

            val linearLayoutSuppliers = view.findViewById<LinearLayout>(R.id.ll_card_suppliers)
            linearLayoutSuppliers.removeAllViews()

            title.text = product.title
            for (supplierPrice in product.supplierPrices) {
                val flagImageView = LayoutInflater.from(view.context).inflate(R.layout.customer_search_card_flag, null) as ImageView
                val priceTextView = LayoutInflater.from(view.context).inflate(R.layout.customer_search_card_price, null) as TextView
                val dashTextView = LayoutInflater.from(view.context).inflate(R.layout.customer_search_card_dash, null) as TextView
                val supplierTextView = LayoutInflater.from(view.context).inflate(R.layout.customer_search_card_supplier, null) as TextView

                val price = supplierPrice.price.amount + supplierPrice.price.currency
                priceTextView.text = price

                val supplier = "@" + supplierPrice.supplierId
                supplierTextView.text = supplier

                linearLayoutFlags.addView(flagImageView)
                linearLayoutPrices.addView(priceTextView)
                linearLayoutDashes.addView(dashTextView)
                linearLayoutSuppliers.addView(supplierTextView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.customer_search_card, parent, false)

        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(data[position])
    }
}