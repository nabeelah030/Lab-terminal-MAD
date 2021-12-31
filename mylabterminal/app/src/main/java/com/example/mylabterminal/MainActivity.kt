package com.example.mylabterminal

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var receiver: MyReceiver
    lateinit var player: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            var start_btn=findViewById<Button>(R.id.btn1)
            var stop_btn=findViewById<Button>(R.id.btn2)
            var reg_recv=findViewById<Button>(R.id.btn3)
            var receiver = MyReceiver()



            IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
                registerReceiver(receiver,it)

            }

            start_btn.setOnClickListener{
                var uri= Uri.parse("file:///android_res/raw/file.mp3");
                player= MediaPlayer.create(this, R.id.file)
                player.isLooping=true
                player.start()
            }
            stop_btn.setOnClickListener{
                super.onDestroy()
                player.stop()
            }
            reg_recv.setOnClickListener({
                IntentFilter("custom_intent").also {
                    registerReceiver(mMessageReceiver,it)
                }
            })
        }

    private fun registerReceiver(receiver: MyReceiver, it: IntentFilter) {

    }


    fun broadcast(view: View) {
            var intent=Intent()
            intent.setAction("custom_intent")
            sendBroadcast(intent)
        }
        private val mMessageReceiver: BroadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                // Extract data included in the Intent
                val message = intent.action
                Toast.makeText(this@MainActivity, message, Toast.LENGTH_LONG).show()
            }
        }
    }
