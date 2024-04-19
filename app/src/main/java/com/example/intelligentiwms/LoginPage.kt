package com.example.intelligentiwms

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login_page)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.loginPage)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val txtNewWarehouse:TextView = findViewById(R.id.newAccount);
        val edtTxtEmail:TextInputLayout = findViewById(R.id.Email);
        val edtTxtEmailInput:EditText = findViewById(R.id.EmailEdtTxt);
        val edtTxtPassword:TextInputLayout = findViewById(R.id.Password);
        val edtTxtPasswordInput : EditText = findViewById(R.id.PasswordEdtTxt);
        val btnLogin:Button = findViewById(R.id.LoginButton);


        txtNewWarehouse.setOnClickListener {
            val intent = Intent(this,RegistrationPage::class.java)
            startActivity(intent)
        }

    }
}