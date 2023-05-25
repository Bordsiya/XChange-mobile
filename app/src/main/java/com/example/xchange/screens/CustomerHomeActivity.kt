package com.example.xchange.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.xchange.GlobalNavigationHandler
import com.example.xchange.GlobalNavigator
import com.example.xchange.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class CustomerHomeActivity : AppCompatActivity(), GlobalNavigationHandler {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.customer_home_activity)

        val customerHomeFragment = CustomerHomeFragment()
        val customerSearchFragment = CustomerSearchFragment()
        val customerSubscriptionsFragment = CustomerSubscriptionsFragment()
        val customerAccountFragment = CustomerAccountFragment()

        setCurrentFragment(customerHomeFragment)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.customerBottomNavigationView)

        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home ->setCurrentFragment(customerHomeFragment)
                R.id.search ->setCurrentFragment(customerSearchFragment)
                R.id.subscriptions ->setCurrentFragment(customerSubscriptionsFragment)
                R.id.account ->setCurrentFragment(customerAccountFragment)
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flCustomerFragment,fragment)
            commit()
        }

    override fun logout() {
        val intent = Intent(
            this@CustomerHomeActivity,
            AuthorizationActivity::class.java)
        startActivity(intent)
        finish()
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