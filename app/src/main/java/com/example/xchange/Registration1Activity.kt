package com.example.xchange

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button

class Registration1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration_activity_1)

        val autoCompleteTextView: AutoCompleteTextView = findViewById(R.id.ac_reg_role)
        val languages = resources.getStringArray(R.array.reg_roles_array)
        val adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_dropdown_item, languages)
        autoCompleteTextView.setAdapter(adapter)
        /*

        autoCompleteTextView.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
                Toast.makeText(
                    this@RegistrationActivity,
                    "YOUR SELECTION IS : " + parent.getItemAtPosition(position).toString(),
                    Toast.LENGTH_SHORT).show();
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
    */

        val btnContinue = findViewById<Button>(R.id.btn_reg_continue)
        btnContinue.setOnClickListener {
            val intent = Intent(
                this@Registration1Activity,
                Registration2Activity::class.java)
            startActivity(intent)
        }
    }
}
