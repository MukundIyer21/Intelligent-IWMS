package com.example.intelligentiwms

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputLayout


class RegistrationPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registration_page2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.RegisterPage)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val edtTxtWarehouseName:TextInputLayout = findViewById(R.id.EnterName);
        val edtTxtWarehouseNameInput:EditText = findViewById(R.id.edtTxtName);
        val edtTxtPassword:TextInputLayout = findViewById(R.id.EnterPassword);
        val edtTxtPasswordInput:EditText = findViewById(R.id.edtTxtPassword);
        val edtTxtEmail:TextInputLayout = findViewById(R.id.EnterEmail);
        val edtTxtEmailInput:EditText = findViewById(R.id.edtTxtEmail);
        val edtTxtCPassword:TextInputLayout = findViewById(R.id.EnterConfirmPassword);
        val edtTxtCPasswordInput:EditText = findViewById(R.id.edtTxtCPassword);
        val btnNextPage:Button = findViewById(R.id.NextButton);

        btnNextPage.setOnClickListener {
            val intent = Intent(this,WarehouseInfo::class.java);
            intent.putExtra("name",edtTxtWarehouseNameInput.text.toString());
            intent.putExtra("email",edtTxtEmailInput.text.toString());
            intent.putExtra("password",edtTxtPasswordInput.text.toString());
            startActivity(intent);
        }

    }

}