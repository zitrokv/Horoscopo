package com.example.horoscopo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class DetalleActivity : AppCompatActivity() {
    lateinit var detalleHoroscopo: Horoscopo
    lateinit var sesion: SessionManager

    lateinit var MenuItemFavorito : MenuItem

    var esFavorito = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)

        sesion = SessionManager(this)

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


        //icono en menu detalle
        esFavorito = sesion.obtenerHoroscopoFavorito()?.equals(detalleHoroscopo.id) ?: false

        /*ImagenFavorito.setOnClickListener{
            if (esFavorito)
            {
                sesion.enviarHoroscopoFavorito("")
            }
            else{
                sesion.enviarHoroscopoFavorito((detalleHoroscopo.id))
            }

            esFavorito = !esFavorito

        }*/

        //findViewById<TextView>(R.id.DetalleTextView).text = getString(detalleHoroscopo.nombre)
        //findViewById<ImageView>(R.id.detalleImageView).setImageResource(detalleHoroscopo.logo)

    }

    fun estableceIconoFavorito (){
        if (esFavorito) {
            MenuItemFavorito.setIcon(R.drawable.ic_favorite_selected)
        }
        else{
            MenuItemFavorito.setIcon(R.drawable.ic_favorite)

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when( item.itemId) {
            R.id.menu_corazon -> {
                if (esFavorito)
                    sesion.enviarHoroscopoFavorito("")
                else
                    sesion.enviarHoroscopoFavorito(detalleHoroscopo.id)

                esFavorito = !esFavorito
                estableceIconoFavorito()
                true
            }
            R.id.menu_share -> {
                val sendIntent = Intent()
                sendIntent.setAction(Intent.ACTION_SEND)
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send")
                sendIntent.setType("text/plain")

                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_activity_detail, menu)
        MenuItemFavorito = menu.findItem(R.id.menu_corazon)
        estableceIconoFavorito()
        return true
    }
}