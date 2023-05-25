package com.example.xchange.screens

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.xchange.GlobalNavigationHandler
import com.example.xchange.GlobalNavigator
import com.example.xchange.R
import com.example.xchange.SessionManager
import com.example.xchange.api_clients.UserClient
import com.example.xchange.model.AuthCredentials
import com.example.xchange.model.TokensRoleResponse
import com.example.xchange.model.ResponseMessage
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection


class AuthorizationActivity : AppCompatActivity() {
    private lateinit var userClient: UserClient

    private lateinit var emailEdt: TextInputEditText
    private lateinit var passwordEdt: TextInputEditText
    private lateinit var registerBtn: Button
    private lateinit var authBtn: Button
    private lateinit var forgotPassTv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.authorization_activity)

        userClient = UserClient()

        initView()
        setListeners()
    }

    private fun initView() {
        emailEdt = findViewById(R.id.te_auth_email)
        passwordEdt = findViewById(R.id.te_auth_password)
        registerBtn = findViewById(R.id.btn_register)
        authBtn = findViewById(R.id.btn_authorize)
        forgotPassTv = findViewById(R.id.tl_forgot_password)
    }

    private fun setListeners() {
        registerBtn.setOnClickListener {
            val intent = Intent(
                this@AuthorizationActivity,
                Registration1Activity::class.java)
            startActivity(intent)
        }

        forgotPassTv.setOnClickListener {
            val intent = Intent(
                this@AuthorizationActivity,
                PasswordRecovery1Activity::class.java)
            startActivity(intent)
        }

        authBtn.setOnClickListener {
            logIn(emailEdt.text.toString(), passwordEdt.text.toString())
        }
    }

    private fun logIn(email: String, password: String) {
        userClient.getUserAPI(this).login(AuthCredentials(email, password))
            .enqueue(object : Callback<Void> {

                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    val role = response.headers()["Role"]
                    if (response.code() == HttpURLConnection.HTTP_OK && role != null) {
                        log(role)
                    }
                    else if (response.code() == HttpURLConnection.HTTP_BAD_REQUEST) {
                        Toast.makeText(this@AuthorizationActivity, "Некорректный логин или пароль", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        Toast.makeText(this@AuthorizationActivity, "Ошибка во время авторизации", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(this@AuthorizationActivity, t.message, Toast.LENGTH_SHORT).show()
                }

            })

    }

    private fun log(role : String) {
        if (role == "Customer") {
            val intent = Intent(this@AuthorizationActivity, CustomerHomeActivity::class.java)
            startActivity(intent)
            finish()
        }
        else if (role == "Supplier") {
            val intent = Intent(this@AuthorizationActivity, SupplierHomeActivity::class.java)
            startActivity(intent)
            finish()
        }
        else {
            Toast.makeText(this@AuthorizationActivity, "В Header ответа находится некорректная роль", Toast.LENGTH_SHORT).show()
        }
    }

}
