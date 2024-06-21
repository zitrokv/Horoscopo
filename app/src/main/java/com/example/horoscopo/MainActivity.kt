package com.example.horoscopo

import android.content.Intent
import android.graphics.Color
import android.icu.lang.UCharacter.VerticalOrientation
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.GONE
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.google.android.material.search.SearchView
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

class MainActivity : AppCompatActivity() {

    lateinit var horoscopoList: List<Horoscopo>
    //lateinit var  recyclerView: RecyclerView
    lateinit var adapter: HoroscopoAdapter


    private var progressBar: ProgressBar? = null
    private var i = 0
    private val handler = Handler()

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

        adapter = HoroscopoAdapter(horoscopoList) { opcionClick -> verDetalle(horoscopoList[opcionClick]) }

        var customAdapter = adapter ?: HoroscopoAdapter(horoscopoList, { opcionClick -> verDetalle(horoscopoList[opcionClick])})
        recyclerView.adapter = customAdapter
        recyclerView.layoutManager = LinearLayoutManager(this, VERTICAL, false)


        recyclerView.setBackgroundColor(Color.rgb(50,67,105))

        // finding progressbar by its id
        progressBar = findViewById<ProgressBar>(R.id.progress_Bar) as ProgressBar
        progressBar!!.visibility = GONE




    }

    override fun onResume() {
        super.onResume()
        adapter.ActualizaDatos(horoscopoList, SessionManager(this).obtenerHoroscopoFavorito().toString())
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
        Log.i("verTraza", getString(horoscopo.nombre))
        /********************/
        progressBar!!.visibility = View.VISIBLE

        i = progressBar!!.progress

        Thread(Runnable {
            // this loop will run until the value of i becomes 99
            while (i < 100) {
                i += 1
                // Update the progress bar and display the current value
                handler.post(Runnable {
                    progressBar!!.progress = i
                    // setting current progress to the textview
                    //txtView!!.text = i.toString() + "/" + progressBar.max
                })
                try {
                    Thread.sleep(10)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }

            // setting the visibility of the progressbar to invisible
            // or you can use View.GONE instead of invisible
            // View.GONE will remove the progressbar
            progressBar!!.visibility = View.INVISIBLE

        }).start()
        /********************/

        val intent :Intent = Intent(this, DetalleActivity::class.java) //::class.java
        intent.putExtra("HOROSCOPO_ID", horoscopo.id)


        //progressBar!!.visibility = View.GONE
        //si quieres ver la activity esto es necesario
        startActivity(intent)

    }
}