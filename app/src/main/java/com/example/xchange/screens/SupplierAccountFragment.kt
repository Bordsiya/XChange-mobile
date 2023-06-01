package com.example.xchange.screens

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.xchange.GlobalNavigationHandler
import com.example.xchange.GlobalNavigator
import com.example.xchange.R
import com.example.xchange.api_clients.UserClient
import com.example.xchange.model.UserInfoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

class SupplierAccountFragment : Fragment(), GlobalNavigationHandler {

    private lateinit var exitAccountTv: TextView
    private lateinit var customerUsernameTv: TextView

    private lateinit var userClient: UserClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.supplier_account_fragment, container, false)
        initView(view)
        setListeners()
        getUserInfo()
        return view
    }

    private fun initView(view: View) {
        exitAccountTv = view.findViewById(R.id.supplier_exit_acc)
        customerUsernameTv = view.findViewById(R.id.supplier_acc_username)

        userClient = UserClient()
    }

    private fun setListeners() {
        exitAccountTv.setOnClickListener {
            val intent = Intent(
                activity,
                AuthorizationActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
    }

    private fun getUserInfo() {
        userClient.getUserAPI(requireActivity()).getUserInfo()
            .enqueue(object : Callback<UserInfoResponse> {

                override fun onResponse(
                    call: Call<UserInfoResponse>,
                    response: Response<UserInfoResponse>
                ) {
                    val clientInfo = response.body()
                    if (response.code() == HttpURLConnection.HTTP_OK && clientInfo != null) {
                        customerUsernameTv.text = clientInfo.username
                    }
                    else {
                        customerUsernameTv.text = ""
                        Toast.makeText(requireActivity(), "Ошибка во время получения информации о пользователе", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<UserInfoResponse>, t: Throwable) {
                    customerUsernameTv.text = ""
                    Toast.makeText(requireActivity(), t.message, Toast.LENGTH_SHORT).show()
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