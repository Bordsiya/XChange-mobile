package com.example.xchange.utils.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.xchange.R
import com.example.xchange.model.Notification
import com.example.xchange.utils.Roles

class NotificationAdapter(private val data: List<Notification>, private val role: Roles) : RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

    class NotificationViewHolder(val view: View, val role: Roles): RecyclerView.ViewHolder(view){

        fun bind(notification: Notification){
            if (role == Roles.CUSTOMER) {
                val titleTv = view.findViewById<TextView>(R.id.tv_customer_notification_title)
                val textTv = view.findViewById<TextView>(R.id.tv_customer_notification_text)

                titleTv.text = notification.status
                textTv.text = notification.text
            }
            else {
                val titleTv = view.findViewById<TextView>(R.id.tv_supplier_notification_title)
                val textTv = view.findViewById<TextView>(R.id.tv_supplier_notification_text)

                titleTv.text = notification.status
                textTv.text = notification.text
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        if (role == Roles.SUPPLIER) {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.supplier_notification_card, parent, false)
            return NotificationViewHolder(v, role)
        }
        else {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.customer_subscription_card, parent, false)
            return NotificationViewHolder(v, role)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.bind(data[position])
    }
}