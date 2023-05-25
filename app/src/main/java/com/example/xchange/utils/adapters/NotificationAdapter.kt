package com.example.xchange.utils.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.xchange.R
import com.example.xchange.model.Notification

class NotificationAdapter(private val data: List<Notification>) : RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

    class NotificationViewHolder(val view: View): RecyclerView.ViewHolder(view){

        fun bind(notification: Notification){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.customer_search_card, parent, false)

        return NotificationViewHolder(v)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.bind(data[position])
    }
}