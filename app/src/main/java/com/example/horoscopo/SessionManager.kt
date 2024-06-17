package com.example.horoscopo

import android.content.Context
import android.content.SharedPreferences

class SessionManager (context: Context) {

    companion object{
        const val HOROSCOPO_FAVORITO = "HOROSCOPO_FAVORITO"
    }
    private val sharedPrefs: SharedPreferences //  para guardar una variable en el SharedPreferences se accede a Editor --> SharedPreferences.Editor

    //no recibe parametros, se inicializa después del constructor (se podría inicializar prefs arriba)
    init{
        sharedPrefs = context.getSharedPreferences("horoscopo_session", Context.MODE_PRIVATE)

    }

    fun enviarHoroscopoFavorito(id: String){
        val editor = sharedPrefs.edit()
        editor.putString(HOROSCOPO_FAVORITO, id)
        editor.apply()

    }

    fun obtenerHoroscopoFavorito() : String? {
        return sharedPrefs.getString(HOROSCOPO_FAVORITO, null)
    }
}
