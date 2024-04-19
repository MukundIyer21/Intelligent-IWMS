package com.example.intelligentiwms

import android.icu.text.UnicodeSet.EntryRange
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.database.database

class WarehouseInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_warehouse_info)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.WareHouseInfo)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnRegister: Button = findViewById(R.id.btnRegisterFinal)
        val warehouseLength:EditText = findViewById(R.id.edtTxtWarehouseLength)
        val warehouseWidth :EditText = findViewById(R.id.edtTxtWarehouseWidth)
        val warehouseHeight :EditText = findViewById(R.id.edtTxtWarehouseHeight)
        val entranceCordinates :EditText = findViewById(R.id.EntranceXYZ)
        val blockedAreaCordinates:EditText = findViewById(R.id.BLockedAreaXYZ)
        val blockedAreaLength :EditText = findViewById(R.id.edtTxtBlockedAreaLength)
        val blockedAreaWidth :EditText = findViewById(R.id.edtTxtBlockedAreaWidth)
        val blockedAreaHeight :EditText = findViewById(R.id.edtTxtBlockedAreaHeight)
        val pathStartCordinates:EditText = findViewById(R.id.edtTxtPathStartXY)
        val pathEndCordinates: EditText = findViewById(R.id.edtTxtPathEndXY)


        btnRegister.setOnClickListener {

            val entrance_x: Int = entranceCordinates.text[1].digitToInt()
            val entrance_y: Int = entranceCordinates.text[3].digitToInt()
            val entrance_z: Int = entranceCordinates.text[5].digitToInt()

            val blocked_x:Int = blockedAreaCordinates.text[1].digitToInt()
            val blocked_y:Int = blockedAreaCordinates.text[3].digitToInt()
            val blocked_z:Int = blockedAreaCordinates.text[5].digitToInt()

            val pathStart_x:Int = pathStartCordinates.text[1].digitToInt()
            val pathStart_y:Int = pathStartCordinates.text[3].digitToInt()

            val pathEnd_x:Int = pathEndCordinates.text[1].digitToInt()
            val pathEnd_y:Int = pathEndCordinates.text[3].digitToInt()

            val wareHouseLen:Int = warehouseLength.text.toString().toInt()
            val wareHouseWid:Int = warehouseWidth.text.toString().toInt()
            val wareHouseHei:Int = warehouseHeight.text.toString().toInt()

            val blockedLen:Int = blockedAreaLength.text.toString().toInt()
            val blockedWid:Int = blockedAreaWidth.text.toString().toInt()
            val blockedHei:Int = blockedAreaHeight.text.toString().toInt()

            val wareHouseName: String? = intent.getStringExtra("name");
            val userEmail:String? = intent.getStringExtra("email");
            val userPassword:String? = intent.getStringExtra("password")

            val database = Firebase.database
            val myRef = database.getReference("warehouse")

            val data = WarehouseData(wareHouseName,
                userEmail,
                userPassword,
                wareHouseLen,
                wareHouseWid,
                wareHouseHei,
                entrance_x,
                entrance_y,
                entrance_z,
                blocked_x,
                blocked_y,
                blocked_z,
                blockedLen,
                blockedWid,
                blockedHei,
                pathStart_x,
                pathStart_y,
                pathEnd_x,
                pathEnd_y
            );

            myRef.setValue(data)
                .addOnSuccessListener {
                    Toast.makeText(this,"done gg",Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Toast.makeText(this,"kys",Toast.LENGTH_SHORT).show()
                }

        }


    }
}