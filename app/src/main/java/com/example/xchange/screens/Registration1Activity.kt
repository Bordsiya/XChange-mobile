package com.example.xchange.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.xchange.R

class Registration1Activity : AppCompatActivity() {
    private lateinit var roleTv: AutoCompleteTextView
    private lateinit var languagesStringArr: Array<String>
    private lateinit var languagesArrAdapter: ArrayAdapter<String>

    private lateinit var continueRegBtn: Button
    private lateinit var usernameEdt: EditText
    private var selectedRole = "Покупатель";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration_activity_1)

        initView()
        setListeners()
    }

    private fun initView() {
        roleTv = findViewById(R.id.ac_reg_role)
        languagesStringArr = resources.getStringArray(R.array.reg_roles_array)
        languagesArrAdapter = ArrayAdapter(this,
            android.R.layout.simple_dropdown_item_1line, languagesStringArr)

        continueRegBtn = findViewById(R.id.btn_reg_continue)
        usernameEdt = findViewById(R.id.te_reg_username)
    }

    private fun setListeners() {
        roleTv.setAdapter(languagesArrAdapter)
        roleTv.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                roleTv.showDropDown()
            }
        }
        roleTv.onItemClickListener = AdapterView.OnItemClickListener { parent, _,
                                                                       position, id ->
            selectedRole = parent.getItemAtPosition(position).toString()
        }

        continueRegBtn.setOnClickListener {
            continueRegistration()
        }
    }

    private fun validateData(): String {
        var errors = ""
        if (usernameEdt.text.toString().isEmpty() || selectedRole.isEmpty()) {
            errors += "Заполните все пустые поля\n"
        }
        return errors
    }

    private fun continueRegistration() {
        val validateResult = validateData()
        if (validateResult.isNotEmpty()) {
            Toast.makeText(this@Registration1Activity, validateResult, Toast.LENGTH_SHORT).show()
            return
        }

        val intent = Intent(
            this@Registration1Activity,
            Registration2Activity::class.java)
        intent.putExtra("username", usernameEdt.text.toString())
        if (selectedRole == "Покупатель") selectedRole = "Customer"
        else if (selectedRole == "Поставщик") selectedRole = "Supplier"
        intent.putExtra("role", selectedRole)
        startActivity(intent)
    }
}
