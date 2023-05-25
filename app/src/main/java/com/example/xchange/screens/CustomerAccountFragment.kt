package com.example.xchange.screens

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.xchange.GlobalNavigationHandler
import com.example.xchange.GlobalNavigator
import com.example.xchange.R

class CustomerAccountFragment : Fragment(), GlobalNavigationHandler {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.customer_account_fragment, container, false)
        val exitAccount = view.findViewById<TextView>(R.id.customer_exit_acc)
        exitAccount.setOnClickListener {
            val intent = Intent(
                activity,
                AuthorizationActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
        return view
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