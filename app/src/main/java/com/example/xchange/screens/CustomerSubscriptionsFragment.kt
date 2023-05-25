package com.example.xchange.screens

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.xchange.GlobalNavigationHandler
import com.example.xchange.GlobalNavigator
import com.example.xchange.R
import com.example.xchange.api_clients.CustomerClient

class CustomerSubscriptionsFragment : Fragment(), GlobalNavigationHandler {
    private lateinit var customerClient: CustomerClient

    private lateinit var recyclerView: RecyclerView
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var subscriptionsAdapter: RecyclerView.Adapter<*>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.customer_subscriptions_fragment, container, false)

        customerClient = CustomerClient()
        manager = LinearLayoutManager(activity)
        recyclerView = view.findViewById(R.id.subscriptions_recycler_view)

        getSubscriptions()

        return view
    }

    private fun getSubscriptions() {
        TODO("добавить получение")
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