package com.example.mylabterminal

import android.Manifest
import android.annotation.SuppressLint
import android.app.Service.START_STICKY
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Telephony
import android.telephony.SmsManager
import android.os.AsyncTask
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private val View.textView: Any
    get() {}

class Recycleview : AppCompatActivity() {
    private val btn2: Any

    override fun onCreate(savedInstanceState: Bundle?) {
        var edi :String = "1234"
        var edi2:String="hiiii"
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycleview)
        var txt = findViewById<TextView>(R.id.txt1)

        //volley String get
        val queue = Volley.newRequestQueue(this)

        btnnn.setOnClickListener{
            val url = "https://run.mocky.io/v3/b8402fc5-ae31-4d98-bced-b5b3fede6d06"
            val string =StringRequest(
                com.android.volley.Request.Method.GET,url,{
                        response ->
                    txt1.text =response
                },
                Response.ErrorListener {
                        error ->
                    txt1.text= error.message
                })
            queue.add(string)
        }
        @SuppressLint("ServiceCast")
        fun isNetworkAvailable(): Boolean {
            val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }

        Intent(this, MyService::class.java).also {
            startService(it)
            Toast.makeText(this,"Services is running ",Toast.LENGTH_LONG).show()
        }


            reccyle.layoutManager= LinearLayoutManager(this)
            reccyle.adapter= customadapter()
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) !=
                PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.RECEIVE_SMS, Manifest.permission.SEND_SMS),
                    111
                )
            }
            else{
                reciveMsg()
            }
            btn2.setOnClickListener{
                var sms = SmsManager.getDefault()
                sms.sendTextMessage(edi,"ME",edi2,null,null)
            }
            btnser.setOnClickListener{
                val intent = Intent(this,servicee::class.java)
                startActivity(intent)
            }


        }

    private fun StringRequest(get: Any, url: String, any: Any, errorListener: Any): Any {

    }


    private fun customadapter(): Any {
        val title = arrayListOf<String>(
            "Name : nabeel , Registration : 1 / Phone:47237589",
            "Name : ahmed , Registration : 2 / Phone:67346642",
            "Name : ali , Registration : 3 / Phone:32586572"
        )
        fun getItemCount(): Int {
            return title.size
        }

        fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {


            val linflater = LayoutInflater.from(parent?.context)
            val newinf = linflater.inflate(R.layout.new_layout,parent,false)
            return viewholder(newinf)


        }

        fun onBindViewHolder(holder: viewholder, position: Int) {
            val titlev = title.get(position)
            holder.v.textView.text=titlev
        }

    }
    class  viewholder(val v: View): RecyclerView.ViewHolder(v){

    }

    }

private fun Any.setOnClickListener(function: () -> Unit) {

}

class MyService {
        val TAG ="MyService"

        fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
            val datastring= intent?.getStringExtra("ExtraData")
            datastring?.let{
                Log.d(TAG,datastring)
            }
            Thread{
                while (true){}
            }.start()
            Log.d(TAG,"i m started")
            return START_STICKY
        }

        fun onDestroy() {
            onDestroy()
            Log.d(TAG,"i m destoryed")
        }

    }

    fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
        ) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            if(requestCode == 111&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                reciveMsg()
            }
        }

        private fun reciveMsg() {
            val br = object : BroadcastReceiver(){
                fun onReceive(context: Context?, intent: Intent?) {
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
                        for (sms in Telephony.Sms.Intents.getMessagesFromIntent(intent)){
                            Toast.makeText(context,sms.displayMessageBody,Toast.LENGTH_LONG).show()

                        }
                    }
                }

            }
            registerReceiver(br, IntentFilter("android.provider.Telephony.SMS_RECEIVED"))
        }

fun registerReceiver(br: BroadcastReceiver, intentFilter: IntentFilter) {

}
}

private fun Any.onDestroy() {
    TODO("Not yet implemented")
}



