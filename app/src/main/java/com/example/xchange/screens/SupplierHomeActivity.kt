package com.example.xchange.screens

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.xchange.GlobalNavigationHandler
import com.example.xchange.GlobalNavigator
import com.example.xchange.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class SupplierHomeActivity : AppCompatActivity(), GlobalNavigationHandler {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.supplier_home_activity)

        val supplierHomeFragment = SupplierHomeFragment()
        val supplierProductsFragment = SupplierProductsFragment()
        val supplierNotificationsFragment = SupplierNotificationsFragment()
        val supplierAccountFragment = SupplierAccountFragment()

        setCurrentFragment(supplierHomeFragment)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.supplierBottomNavigationView)

        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home ->setCurrentFragment(supplierHomeFragment)
                R.id.products ->setCurrentFragment(supplierProductsFragment)
                R.id.notifications ->setCurrentFragment(supplierNotificationsFragment)
                R.id.account ->setCurrentFragment(supplierAccountFragment)
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flSupplierFragment,fragment)
            commit()
        }

    override fun logout() {
        val intent = Intent(
            this@SupplierHomeActivity,
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