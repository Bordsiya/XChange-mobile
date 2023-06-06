package com.example.xchange.screens

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.xchange.GlobalNavigationHandler
import com.example.xchange.GlobalNavigator
import com.example.xchange.R
import com.example.xchange.api_clients.CustomerClient
import com.example.xchange.api_clients.SupplierClient
import com.example.xchange.model.Product
import com.example.xchange.model.SearchRequest
import com.example.xchange.model.UploadProductsRequest
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SupplierProductsFragment : Fragment(), GlobalNavigationHandler {

    private lateinit var productsEt: TextInputEditText
    private lateinit var sendBtn: AppCompatButton
    private lateinit var viewBtn: AppCompatButton

    private lateinit var supplierClient: SupplierClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.supplier_products_fragment, container, false)
        initView(view)
        setListeners()
        return view
    }

    private fun initView(view: View) {
        productsEt = view.findViewById(R.id.tiet_input_products)
        sendBtn = view.findViewById(R.id.btn_input_products)
        viewBtn = view.findViewById(R.id.btn_view_products)
        supplierClient = SupplierClient()
    }

    private fun setListeners() {
        sendBtn.setOnClickListener {
            sendProducts()
        }
        viewBtn.setOnClickListener {
            viewProducts()
        }
    }

    private fun sendProducts() {
        supplierClient.getSupplierAPI(requireActivity()).uploadProducts(buildSendRequest())
            .enqueue(object : Callback<Void> {

                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    productsEt.text?.clear()
                    Toast.makeText(requireContext(), "Товары успешно загружены", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
                }

            })
    }

    private fun buildSendRequest() : UploadProductsRequest {
        return UploadProductsRequest(
            productsEt.text.toString()
        )
    }

    private fun viewProducts() {
        productsEt.text?.clear()
        supplierClient.getSupplierAPI(requireActivity()).viewProducts()
            .enqueue(object : Callback<List<Product>> {

                override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                    val products = response.body()
                    if (response.isSuccessful && products != null) {
                        productsEt.setText(products.toString())
                    }
                    else {
                        Toast.makeText(requireContext(), "Ошибка во время получения списка товаров", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                    Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
                }

            })
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