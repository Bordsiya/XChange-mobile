package com.example.xchange

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class PasswordRecovery2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.password_recovery_activity_2)

        val btnContinue = findViewById<Button>(R.id.btn_pass_rec2_continue)
        btnContinue.setOnClickListener {
            val intent = Intent(
                this@PasswordRecovery2Activity,
                PasswordRecovery3Activity::class.java)
            startActivity(intent)
        }
    }
}