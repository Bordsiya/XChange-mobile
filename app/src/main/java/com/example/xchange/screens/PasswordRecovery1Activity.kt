package com.example.xchange.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.xchange.R

class PasswordRecovery1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.password_recovery_activity_1)

        val btnContinue = findViewById<Button>(R.id.btn_pass_rec_continue)
        btnContinue.setOnClickListener {
            val intent = Intent(
                this@PasswordRecovery1Activity,
                PasswordRecovery2Activity::class.java)
            startActivity(intent)
        }
    }
}