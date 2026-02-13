package com.menak.activity1
import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


import android.content.Intent

import androidx.activity.enableEdgeToEdge


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)

            val androidVersionID = findViewById<TextView>(R.id.VersionTXT)
            val sdkLevelID = findViewById<TextView>(R.id.SDKTXT)
            val DeviceModelID = findViewById<TextView>(R.id.ModelTXT)

            val androidVersion = Build.VERSION.RELEASE
            val sdkLevel = Build.VERSION.SDK_INT
            val deviceModel = Build.MODEL
            val manufacturer = Build.MANUFACTURER

            val androidVersionTXT = "Android Version: $androidVersion"
            val sdkLevelTXT = "SDK Level: $sdkLevel"
            val deviceModelTXT = "Device Model: $deviceModel - $manufacturer"

            androidVersionID.text = androidVersionTXT
            sdkLevelID.text = sdkLevelTXT
            DeviceModelID.text = deviceModelTXT


            val NotesBTN = findViewById<Button>(R.id.NotesBTN)
            val LinksBTN = findViewById<Button>(R.id.LinksBTN)

            NotesBTN.setOnClickListener {
                val intent = Intent(this, NotesActivity::class.java)
                startActivity(intent)
            }
            LinksBTN.setOnClickListener {
                val intent = Intent(this, LinksActivity::class.java)
                startActivity(intent)
            }




            insets
        }
    }
}