package com.example.horoscopo

import android.content.Intent
import android.icu.lang.UCharacter.VerticalOrientation
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.google.android.material.search.SearchView
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

class MainActivity : AppCompatActivity() {

    lateinit var horoscopoList: List<Horoscopo>
    //lateinit var  recyclerView: RecyclerView
    lateinit var adapter: HoroscopoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*for (horoscope in horoscopoList)
            Log.i("HOROSCOPO", horoscope.nombre)

         */

        horoscopoList = HoroscopoProvider.findAll()

        //var customAdapter = HoroscopoAdapter(horoscopoList, { opcionClick -> verDetalle(horoscopoList[opcionClick])})
         //adapter = customAdapter
        var recyclerView : RecyclerView= findViewById(R.id.listaHoroscopoRecyclerView)

        adapter = HoroscopoAdapter(horoscopoList, { opcionClick -> verDetalle(horoscopoList[opcionClick])})

        var customAdapter = adapter ?: HoroscopoAdapter(horoscopoList, { opcionClick -> verDetalle(horoscopoList[opcionClick])})
        recyclerView.adapter = customAdapter
        recyclerView.layoutManager = LinearLayoutManager(this, VERTICAL, false)


    }

    override fun onResume() {
        super.onResume()
        adapter.ActualizaDatos(horoscopoList)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        //var inflater:MenuInflater = menuInflater
        //inflater.inflate(R.menu.menu_activity_main, menu)

        menuInflater.inflate(R.menu.menu_activity_main, menu)


        val searchViewItem = menu.findItem(R.id.buscar)
        val searchView = searchViewItem.actionView as androidx.appcompat.widget.SearchView
        searchView.setOnQueryTextListener(object: androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText !=null) {
                    horoscopoList = HoroscopoProvider.findAll().filter { getString(it.nombre).contains(newText, true)
                            || getString(it.fecha).contains(newText, true) }
                    adapter.ActualizaDatos(horoscopoList, newText)

                }
                return true

            }
        })
        return true
        //return super.onCreateOptionsMenu(menu)
    }

    fun verDetalle(horoscopo: Horoscopo){
        //Log.i("verTraza", getString(horoscopo.nombre))
        val intent :Intent = Intent(this, DetalleActivity::class.java) //::class.java
        intent.putExtra("HOROSCOPO_ID", horoscopo.id)

        //si quieres ver la activity esto es necesario
        startActivity(intent)

    }
}