package com.example.horoscopo

import android.icu.lang.UCharacter.VerticalOrientation
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

class MainActivity : AppCompatActivity() {

    val horoscopoList:List<Horoscopo> = listOf(
        //instanciar la clase horoscopo para los 12 signos
        Horoscopo("aries", "Aries", 0),
        Horoscopo("tauro", "Tauro", 0),
        Horoscopo("geminis", "Geminis", 0),
        Horoscopo("cancer", "Cancer", 0),
        Horoscopo("leo", "Leo", 0),
        Horoscopo("virgo", "Virgo", 0),
        Horoscopo("libra", "Libra", 0),
        Horoscopo("escorpio", "Escorpio", 0),
        Horoscopo("sagitario", "Sagitario", 0),
        Horoscopo("capricornio", "Capricornio", 0),
        Horoscopo("acuario", "Acuario", 0),
        Horoscopo("piscis", "Piscis", 0),
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*for (horoscope in horoscopoList)
            Log.i("HOROSCOPO", horoscope.nombre)

         */

        val customAdapter = HoroscopoAdapter(horoscopoList)

        val recyclerView: RecyclerView = findViewById(R.id.listaHoroscopoRecyclerView)
        recyclerView.adapter = customAdapter
        recyclerView.layoutManager = LinearLayoutManager(this, VERTICAL, false)


    }
}