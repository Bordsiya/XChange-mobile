package com.example.xchange.screens

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import com.example.xchange.model.Notification
import com.example.xchange.utils.Roles
import com.example.xchange.utils.adapters.NotificationAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Timer
import java.util.TimerTask

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
        val timer = Timer()
        val timerTask: TimerTask = object : TimerTask() {
            override fun run() {
                customerClient.getCustomerAPI(requireActivity()).getSubscriptions()
                    .enqueue(object : Callback<List<Notification>> {

                        override fun onResponse(
                            call: Call<List<Notification>>,
                            response: Response<List<Notification>>
                        ) {
                            val notifications = response.body()
                            if (response.isSuccessful && notifications != null) {
                                recyclerView.apply {
                                    subscriptionsAdapter = NotificationAdapter(
                                        notifications, Roles.CUSTOMER
                                    )
                                    layoutManager = manager
                                    adapter = subscriptionsAdapter
                                }
                            } else {
                                Log.d("timer_task", "ошибка во время получения нотификашек")
                            }
                        }

                        override fun onFailure(call: Call<List<Notification>>, t: Throwable) {
                            Log.d("timer_task", t.message.toString())
                        }

                    })
            }
        }
        timer.scheduleAtFixedRate(timerTask, 0, 1 * 60 * 1000)
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