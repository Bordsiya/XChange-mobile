package com.example.xchange.screens

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.xchange.GlobalNavigationHandler
import com.example.xchange.GlobalNavigator
import com.example.xchange.R
import com.google.android.material.card.MaterialCardView

class CustomerSubscriptionsFragment : Fragment(), GlobalNavigationHandler {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.customer_subscriptions_fragment, container, false)

        val supplierLinearLayout = view.findViewById<LinearLayout>(R.id.ll_suppliers)
        val addedSuppliersLinearLayout = view.findViewById<LinearLayout>(R.id.ll_added_suppliers)

        supplierLinearLayout.setOnClickListener {
            val requestCard = view.findViewById<MaterialCardView>(R.id.mcv_request_card)
            val requestCardClickedModel = view.findViewById<TextView>(R.id.tv_request_card_model)
            val requestCardClickedSupplier = view.findViewById<TextView>(R.id.tv_request_card_supplier)

            requestCard.visibility=View.VISIBLE
        }

        addedSuppliersLinearLayout.setOnClickListener {
            val requestCard = view.findViewById<MaterialCardView>(R.id.mcv_request_card)
            val requestCardClickedModel = view.findViewById<TextView>(R.id.tv_request_card_model)
            val requestCardClickedSupplier = view.findViewById<TextView>(R.id.tv_request_card_supplier)

            requestCard.visibility=View.VISIBLE
        }

        val requestCardCancel = view.findViewById<TextView>(R.id.tv_request_card_cancel)
        val requestCardSend = view.findViewById<TextView>(R.id.tv_request_card_send)

        requestCardCancel.setOnClickListener {
            val requestCard = view.findViewById<MaterialCardView>(R.id.mcv_request_card)
            requestCard.visibility=View.INVISIBLE
        }

        requestCardSend.setOnClickListener {
            val requestCard = view.findViewById<MaterialCardView>(R.id.mcv_request_card)
            requestCard.visibility=View.INVISIBLE
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