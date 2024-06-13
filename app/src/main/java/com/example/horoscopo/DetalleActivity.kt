package com.example.horoscopo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import java.time.LocalDate
import java.time.format.DateTimeFormatter

lateinit var detalleHoroscopo: Horoscopo
class DetalleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)

        val id = intent.getStringExtra("HOROSCOPO_ID")
        //val id = intent.getIntExtra("HOROSCOPO_ID",-1)
        detalleHoroscopo = HoroscopoProvider.findById(id!!)

        //findViewById<TextView>(R.id.DetalleTextView).setText(id)
        findViewById<TextView>(R.id.DetalleTextView).setText(detalleHoroscopo.nombre)
        findViewById<ImageView>(R.id.detalleImageView).setImageResource(detalleHoroscopo.logo)
        findViewById<ImageView>(R.id.waterImageView).setImageResource(detalleHoroscopo.logo)
        findViewById<ImageView>(R.id.waterImageView).imageAlpha = 28
        findViewById<ImageView>(R.id.waterImageView).setBackgroundColor(255)
        findViewById<TextView>(R.id.prediccionTextView).setText(LocalDate.now().format(
            DateTimeFormatter.ofPattern("dd-MM-yyyy")).toString())

        //findViewById<TextView>(R.id.DetalleTextView).text = getString(id)
    }
}