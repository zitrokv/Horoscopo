package com.example.horoscopo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.net.ssl.HttpsURLConnection


class DetalleActivity : AppCompatActivity() {
    lateinit var detalleHoroscopo: Horoscopo
    lateinit var sesion: SessionManager
    lateinit var textoDelDia : TextView
    lateinit var MenuItemFavorito : MenuItem

    var esFavorito = false


    init {
        textoDelDia = findViewById(R.id.textoDelDia)
    }
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


        //supportActionBar?.setTitle(detalleHoroscopo.nombre)
        //supportActionBar?.setSubtitle(detalleHoroscopo.fecha)
        //supportActionBar?.setDisplayHomeAsUpEnabled(true)

        getDailyHoroscope()

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


    fun getDailyHoroscope() {
        // Llamada en hilo secundario
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Declaramos la url
                val url = URL("https://horoscope-app-api.vercel.app/api/v1/get-horoscope/daily?sign=${detalleHoroscopo.id}&day=TODAY")
                val con = url.openConnection() as HttpsURLConnection
                con.requestMethod = "GET"
                val responseCode = con.responseCode
                Log.i("HTTP", "Response Code :: $responseCode")

                // Preguntamos si hubo error o no
                if (responseCode == HttpsURLConnection.HTTP_OK) { // Ha ido bien
                    // Metemos el cuerpo de la respuesta en un BurfferedReader
                    val bufferedReader = BufferedReader(InputStreamReader(con.inputStream))
                    var inputLine: String?
                    val response = StringBuffer()
                    while (bufferedReader.readLine().also { inputLine = it } != null) {
                        response.append(inputLine)
                    }
                    bufferedReader.close()

                    // Parsear JSON
                    val json = JSONObject(response.toString())
                    val result =  json.getJSONObject("data").getString("horoscope_data")

                    // Ejecutamos en el hilo principal
                    /*CoroutineScope(Dispatchers.Main).launch {

                    }*/
                    runOnUiThread {
                        textoDelDia.text = result
                    }

                } else { // Hubo un error
                    Log.w("HTTP", "Response :: Hubo un error")
                }
            } catch (e: Exception) {
                Log.e("HTTP", "Response Error :: ${e.stackTraceToString()}")
            }
        }
    }
}