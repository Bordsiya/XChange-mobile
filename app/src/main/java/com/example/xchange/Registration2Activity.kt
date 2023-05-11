package com.example.xchange

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Registration2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration_activity_2)

        val btnRegister = findViewById<Button>(R.id.btn_reg2)
        btnRegister.setOnClickListener {
            val intent = Intent(
                this@Registration2Activity,
                AuthorizationActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}