package com.example.xchange.screens

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.xchange.GlobalNavigationHandler
import com.example.xchange.GlobalNavigator
import com.example.xchange.R
import com.example.xchange.api_clients.CustomerClient
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
                            messageEt.setText("По вашему запросу ничего не найдено")
                            messageEt.visibility = View.VISIBLE
                        }
                        else {
                            recyclerView.apply {
                                productsAdapter = ProductAdapter(products, customerClient, requireActivity())
                                layoutManager = manager
                                adapter = productsAdapter
                            }
                        }
                    }
                    else {
                        messageEt.setText("По вашему запросу ничего не найдено")
                        messageEt.visibility = View.VISIBLE
                    }
                }

                override fun onFailure(call: Call<List<Product>>, t: Throwable) {
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