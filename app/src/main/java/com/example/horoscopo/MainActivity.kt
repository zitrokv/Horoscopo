package com.example.horoscopo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    val horoscopoList:List<Horoscopo> = listOf(
        //instanciar la clase horoscopo para los 12 signos
        Horoscopo("aries", "Aries", 0),
        Horoscopo("tauro", "Tauro", 0),
        Horoscopo("geminis", "Geminis", 0),
        Horoscopo("aries", "Cancer", 0),
        Horoscopo("aries", "Leo", 0),
        Horoscopo("aries", "Virgo", 0),
        Horoscopo("aries", "Libra", 0),
        Horoscopo("aries", "Escorpio", 0),
        Horoscopo("aries", "Sagitario", 0),
        Horoscopo("aries", "Capricornio", 0),
        Horoscopo("aries", "Acuario", 0),
        Horoscopo("aries", "Piscis", 0),
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (horoscope in horoscopoList)
            Log.i("HOROSCOPO", horoscope.nombre)
    }
}