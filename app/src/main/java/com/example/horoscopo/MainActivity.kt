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
        Horoscopo("aries", R.string.horoscope_name_aries, R.string.horoscope_date_aries,R.drawable.aries_icon),
        Horoscopo("tauro", R.string.horoscope_name_taurus, R.string.horoscope_date_taurus,R.drawable.taurus_icon),
        Horoscopo("geminis",R.string.horoscope_name_gemini,R.string.horoscope_date_gemini, R.drawable.gemini_icon),
        Horoscopo("cancer",R.string.horoscope_name_cancer, R.string.horoscope_date_cancer,R.drawable.cancer_icon),
        Horoscopo("leo", R.string.horoscope_name_leo, R.string.horoscope_date_leo,R.drawable.leo_icon),
        Horoscopo("virgo", R.string.horoscope_name_virgo, R.string.horoscope_date_virgo,R.drawable.virgo_icon),
        Horoscopo("libra", R.string.horoscope_name_libra, R.string.horoscope_date_libra,R.drawable.libra_icon),
        Horoscopo("escorpio", R.string.horoscope_name_scorpio,R.string.horoscope_date_scorpio,R.drawable.scorpio_icon),
        Horoscopo("sagitario", R.string.horoscope_name_sagittarius,R.string.horoscope_date_sagittarius, R.drawable.sagittarius_icon),
        Horoscopo("capricornio", R.string.horoscope_name_capricorn,R.string.horoscope_date_capricorn,R.drawable.capricorn_icon),
        Horoscopo("acuario", R.string.horoscope_name_aquarius , R.string.horoscope_date_aquarius,R.drawable.aquarius_icon),
        Horoscopo("piscis", R.string.horoscope_name_pisces, R.string.horoscope_date_pisces,R.drawable.pisces_icon),
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