package com.example.xchange

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class AuthorizationActivity : AppCompatActivity() {
    private val DEBUG_USER_TYPE = "Supplier"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.authorization_activity)

        val btnRegister = findViewById<Button>(R.id.btn_register)
        btnRegister.setOnClickListener {
            val intent = Intent(
                this@AuthorizationActivity,
                Registration1Activity::class.java)
            startActivity(intent)
        }

        val passRecover = findViewById<TextView>(R.id.tl_forgot_password)
        passRecover.setOnClickListener {
            val intent = Intent(
                this@AuthorizationActivity,
                PasswordRecovery1Activity::class.java)
            startActivity(intent)
        }

        val btnAuth = findViewById<Button>(R.id.btn_authorize)
        btnAuth.setOnClickListener {
            if (DEBUG_USER_TYPE.contentEquals("Customer")) {
                val intent = Intent(
                    this@AuthorizationActivity,
                    CustomerHomeActivity::class.java)
                startActivity(intent)
                finish()
            }
            else {
                val intent = Intent(
                    this@AuthorizationActivity,
                    SupplierHomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

    }
}
