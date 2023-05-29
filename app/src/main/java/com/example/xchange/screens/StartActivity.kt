package com.example.xchange.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.xchange.GlobalNavigationHandler
import com.example.xchange.GlobalNavigator
import com.example.xchange.R
import com.example.xchange.SessionManager
import com.example.xchange.api_clients.UserClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

class MainActivity : AppCompatActivity(), GlobalNavigationHandler {
    private lateinit var sessionManager: SessionManager
    private lateinit var userClient: UserClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_activity)

        userClient = UserClient()
        sessionManager = SessionManager(this)

        supportActionBar?.hide()
        Handler(Looper.getMainLooper()).postDelayed({
            tryToLogIn()
        }, 3000)
    }

    private fun tryToLogIn() {
        userClient.getUserAPI(this).loginWithAccessToken()
            .enqueue(object : Callback<Void> {

                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    val role = response.headers()["Role"]
                    if (role != null) {
                        log(role)
                    }
                    else {
                        logout()
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    logout()
                }

            })
    }

    private fun log(role : String) {
        if (role == "Customer") {
            val intent = Intent(this@MainActivity, CustomerHomeActivity::class.java)
            startActivity(intent)
            finish()
        }
        else if (role == "Supplier") {
            val intent = Intent(this@MainActivity, SupplierHomeActivity::class.java)
            startActivity(intent)
            finish()
        }
        else {
            logout()
        }
    }

    override fun logout() {
        val intent = Intent(this@MainActivity, AuthorizationActivity::class.java)
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