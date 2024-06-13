package com.example.horoscopo

import android.content.Intent
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

    lateinit var horoscopoList: List<Horoscopo>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*for (horoscope in horoscopoList)
            Log.i("HOROSCOPO", horoscope.nombre)

         */

        horoscopoList = HoroscopoProvider.findAll()

        val customAdapter = HoroscopoAdapter(horoscopoList, { opcionClick -> verDetalle(horoscopoList[opcionClick])})

        val recyclerView: RecyclerView = findViewById(R.id.listaHoroscopoRecyclerView)
        recyclerView.adapter = customAdapter
        recyclerView.layoutManager = LinearLayoutManager(this, VERTICAL, false)


    }

    fun verDetalle(horoscopo: Horoscopo){
        //Log.i("verTraza", getString(horoscopo.nombre))
        val intent :Intent = Intent(this, DetalleActivity::class.java) //::class.java
        intent.putExtra("HOROSCOPO_ID", horoscopo.id)

        //si quieres ver la activity esto es necesario
        startActivity(intent)

    }
}