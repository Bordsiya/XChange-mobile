package com.example.xchange.screens

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.xchange.GlobalNavigationHandler
import com.example.xchange.GlobalNavigator
import com.example.xchange.R
import com.example.xchange.api_clients.CustomerClient
import com.example.xchange.model.NotificationSubscriptionResponse
import com.example.xchange.model.Product
import com.example.xchange.model.SearchRequest
import com.example.xchange.utils.adapters.ProductAdapter
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CustomerSearchFragment : Fragment(), GlobalNavigationHandler {
    private lateinit var recyclerView: RecyclerView
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var productsAdapter: RecyclerView.Adapter<*>

    private lateinit var searchBtn: Button
    private lateinit var searchEt: EditText
    private lateinit var messageEt: MaterialTextView

    private lateinit var customerClient: CustomerClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.customer_search_fragment, container, false)

        initView(view)
        setListeners()
        return view
    }

    private fun initView(view: View) {
        searchBtn = view.findViewById(R.id.mb_btn_search)
        searchEt = view.findViewById(R.id.et_search)
        messageEt = view.findViewById(R.id.products_message_tv)

        manager = LinearLayoutManager(activity)
        recyclerView = view.findViewById(R.id.products_recycler_view)

        customerClient = CustomerClient()
    }

    private fun setListeners() {
        searchBtn.setOnClickListener {
            getProducts()
        }
    }

    private fun getProducts() {
        recyclerView.visibility = View.VISIBLE
        messageEt.visibility = View.INVISIBLE
        customerClient.getCustomerAPI(requireActivity()).searchProducts(buildRequest())
            .enqueue(object : Callback<List<Product>> {

                override fun onResponse(
                    call: Call<List<Product>>,
                    response: Response<List<Product>>
                ) {
                    val products = response.body()
                    if (response.isSuccessful && products != null) {
                        if (products.isEmpty()) {
                            recyclerView.visibility = View.INVISIBLE
                            messageEt.setText("По вашему запросу ничего не найдено")
                            messageEt.visibility = View.VISIBLE
                        }
                        else {
                            val isSubscribedList = MutableList(products.size) { false }
                            customerClient.getCustomerAPI(requireActivity()).getUsersSubscriptions()
                                .enqueue(object: Callback<List<NotificationSubscriptionResponse>> {

                                    override fun onResponse(
                                        call: Call<List<NotificationSubscriptionResponse>>,
                                        response: Response<List<NotificationSubscriptionResponse>>
                                    ) {
                                        val subscriptions = response.body()
                                        if (response.isSuccessful && subscriptions != null) {
                                            for (i in products.indices) {
                                                if (subscriptions.filter {
                                                        it.modelId == products[i].modelId
                                                }.size == 1) isSubscribedList[i] = true
                                            }

                                            recyclerView.apply {
                                                productsAdapter = ProductAdapter(products, isSubscribedList, customerClient, requireActivity())
                                                layoutManager = manager
                                                adapter = productsAdapter
                                            }
                                        }
                                        else {
                                            recyclerView.visibility = View.INVISIBLE
                                            Toast.makeText(requireContext(),
                                                "Ошибка при получении подписок пользователя",
                                                Toast.LENGTH_SHORT).show()
                                        }
                                    }

                                    override fun onFailure(
                                        call: Call<List<NotificationSubscriptionResponse>>,
                                        t: Throwable
                                    ) {
                                        recyclerView.visibility = View.INVISIBLE
                                        Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
                                    }

                                })
                        }
                    }
                    else {
                        recyclerView.visibility = View.INVISIBLE
                        messageEt.setText("По вашему запросу ничего не найдено")
                        messageEt.visibility = View.VISIBLE
                    }
                }

                override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                    recyclerView.visibility = View.INVISIBLE
                    messageEt.setText("По вашему запросу ничего не найдено")
                    messageEt.visibility = View.VISIBLE
                }

            })
    }

    private fun buildRequest() : SearchRequest {
        return SearchRequest(
            searchEt.text.toString()
        )
    }

    override fun logout() {
        val intent = Intent(
            activity,
            AuthorizationActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

    override fun onStart() {
        super.onStart()
        GlobalNavigator.registerHandler(this)
    }

    override fun onStop() {
        super.onStop()
        GlobalNavigator.unregisterHandler()
    }
}