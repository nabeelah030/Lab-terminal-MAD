package com.example.mylabterminal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class servicee : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_servicee)
        btn11.setOnClickListener{
            Intent(this,MyService::class.java).also {
                val startService = startService(it)
                txt1.text="Services is Running"
            }
        }
        btn10.setOnClickListener{
            Intent(this,MyService::class.java).also {
                val stopService = stopService(it)
                txt1.text="Services is Stop"
            }
        }
    }
}