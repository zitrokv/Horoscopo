package com.example.horoscopo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DetalleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)

        val id = intent.getStringExtra("HOROSCOPO_ID")
        //val id = intent.getIntExtra("HOROSCOPO_ID",-1)

        findViewById<TextView>(R.id.DetalleTextView).setText(id)
        //findViewById<TextView>(R.id.DetalleTextView).text = getString(id)
    }
}