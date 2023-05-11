package com.example.xchange

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class PasswordRecovery3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.password_recovery_activity_3)

        val btnChangePassword = findViewById<Button>(R.id.btn_pass_rec3_change_password)
        btnChangePassword.setOnClickListener {
            val intent = Intent(
                this@PasswordRecovery3Activity,
                AuthorizationActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}