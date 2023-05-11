package com.example.xchange

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.material.card.MaterialCardView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CustomerSubscriptionsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CustomerSubscriptionsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CustomerSubscriptionsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CustomerSubscriptionsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}