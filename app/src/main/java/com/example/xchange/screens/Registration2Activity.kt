package com.example.xchange.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.xchange.R
import com.example.xchange.SessionManager
import com.example.xchange.api_clients.UserClient
import com.example.xchange.model.RegisterCredentials
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

class Registration2Activity : AppCompatActivity() {
    private lateinit var sessionManager: SessionManager
    private lateinit var userClient: UserClient

    private lateinit var passwordEt: EditText
    private lateinit var repeatedPasswordEt: EditText
    private lateinit var regBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration_activity_2)

        userClient = UserClient()
        sessionManager = SessionManager(this)

        initView()
        setListeners()
    }
    private fun initView() {
        passwordEt = findViewById(R.id.te_reg2_password)
        repeatedPasswordEt = findViewById(R.id.te_reg2_repeated_password)
        regBtn = findViewById(R.id.btn_reg2)
    }

    private fun setListeners() {
        regBtn.setOnClickListener {
            register()
        }
    }

    private fun register() {
        val validateResult = validateData()
        if (validateResult.isNotEmpty()) {
            Toast.makeText(this@Registration2Activity, validateResult, Toast.LENGTH_SHORT).show()
            return
        }
        userClient.getUserAPI(this).register(buildRegisterRequest())
            .enqueue(object : Callback<Void> {

                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    val role = response.headers()["Role"]
                    if (response.code() == HttpURLConnection.HTTP_OK && role != null) {
                        log(role)
                    }
                    else if (response.code() == HttpURLConnection.HTTP_BAD_REQUEST) {
                        Toast.makeText(this@Registration2Activity, "Невалидные данные для регистрации", Toast.LENGTH_SHORT).show()
                        returnToAuth()
                    }
                    else {
                        Toast.makeText(this@Registration2Activity, "Ошибка во время регистрации", Toast.LENGTH_SHORT).show()
                        returnToAuth()
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(this@Registration2Activity, t.message, Toast.LENGTH_SHORT).show()
                    returnToAuth()
                }

            })
    }

    private fun validateData(): String {
        var errors = ""
        if (passwordEt.text.toString() != repeatedPasswordEt.text.toString()) {
            errors += "Пароли должны совпадать\n"
        }
        if (passwordEt.text.toString().isEmpty() || repeatedPasswordEt.text.toString().isEmpty()) {
            errors += "Пароли не могут быть пустыми\n"
        }
        return errors
    }

    private fun buildRegisterRequest() : RegisterCredentials {
        return RegisterCredentials(
            intent.getStringExtra("email")!!,
            intent.getStringExtra("username")!!,
            passwordEt.text.toString(),
            intent.getStringExtra("role")!!
        )
    }

    private fun log(role: String) {
        if (role == "Customer") {
            val intent = Intent(this@Registration2Activity, CustomerHomeActivity::class.java)
            startActivity(intent)
            finish()
        }
        else if (role == "Supplier") {
            val intent = Intent(this@Registration2Activity, SupplierHomeActivity::class.java)
            startActivity(intent)
            finish()
        }
        else {
            Toast.makeText(this@Registration2Activity, "В Header ответа находится некорректная роль", Toast.LENGTH_SHORT).show()
            returnToAuth()
        }
    }

    private fun returnToAuth() {
        val intent = Intent(this@Registration2Activity, AuthorizationActivity::class.java)
        startActivity(intent)
        finish()
    }

}