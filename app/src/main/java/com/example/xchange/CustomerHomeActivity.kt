package com.example.xchange

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class CustomerHomeActivity : AppCompatActivity() {
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
                R.id.home->setCurrentFragment(customerHomeFragment)
                R.id.search->setCurrentFragment(customerSearchFragment)
                R.id.subscriptions->setCurrentFragment(customerSubscriptionsFragment)
                R.id.account->setCurrentFragment(customerAccountFragment)
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flCustomerFragment,fragment)
            commit()
        }
}