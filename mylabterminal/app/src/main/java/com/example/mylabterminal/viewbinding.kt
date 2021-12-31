package com.example.mylabterminal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mylabterminal.databinding.ActivityMainBinding.inflate

class viewbinding : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewbinding)
        var mylabterminal = inflate(layoutInflater)
        val view = mylabterminal.root
        mylabterminal.binder.setOnClickListener {
            Toast.makeText(applicationContext, "Ok", Toast.LENGTH_SHORT).show()
        }


    }
}