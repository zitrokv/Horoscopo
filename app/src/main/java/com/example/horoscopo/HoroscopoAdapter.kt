package com.example.horoscopo

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.Color
import android.text.SpannableString
import android.text.style.BackgroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.absoluteValue

class HoroscopoAdapter (private var dataSet: List<Horoscopo>, private val onItemClickListener: (Int) -> Unit) :
RecyclerView.Adapter<ViewHolder<Any?>>() {

    private var highlightText: String? = null

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder<Any?> {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_horoscopo, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder<Any?>, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        //viewHolder.textView.text = dataSet[position].nombre
        /*viewHolder.textView.setText(dataSet[position].nombre)
        viewHolder.fechasTextView.setText(dataSet[position].fecha)
        viewHolder.imagenImgView.setImageResource(dataSet[position].logo)
*/
        viewHolder.render(dataSet[position])

        if (highlightText != null) {
            viewHolder.highlight(highlightText!!)
        }

        //aqui declaramos el evento de click onItemClickListener
        viewHolder.itemView.setOnClickListener {onItemClickListener(position)}


    }

    //este metodo sirve para actualizar los datos
    /*fun ActualizaDatos(newDataSet: List<Horoscopo>){
        //newDataSet.forEach{ elem -> (elem as ImageView).setBackgroundColor (Color.BLUE) }
        //newDataSet.forEach{ elem -> ((elem.nombre.toString().contains("ar",ignoreCase = true)) ) }
        dataSet = newDataSet
        //newDataSet.forEach{elem -> ((getItemViewType(elem.logo)) as ImageView).setBackgroundColor(Color.BLUE)}
        notifyDataSetChanged() // comunica con el recycler view todito el listado de nuevo
    }*/

    // Este método sirve para actualizar los datos
    fun ActualizaDatos (newDataSet: List<Horoscopo>) {
        ActualizaDatos(newDataSet, null)
    }

    fun ActualizaDatos(newDataSet: List<Horoscopo>, highlight: String?) {
        this.highlightText = highlight
        dataSet = newDataSet
        notifyDataSetChanged()
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size


}

@SuppressLint("ResourceAsColor")
class ViewHolder<Bitmap>(view: View) : RecyclerView.ViewHolder(view) {
    val textView: TextView
    val fechasTextView: TextView
    val imagenImgView :ImageView
    val favoritoImageView: ImageView
    val celdaHoroscopo : LinearLayout
        get() {
            return this.celdaHoroscopo
        }

    init {
        // Define click listener for the ViewHolder's View
        textView = view.findViewById(R.id.nombreHoroscopoTextView)
        imagenImgView = view.findViewById<ImageView?>(R.id.imagenImgView)
        fechasTextView = view.findViewById(R.id.fechasTextView)
        favoritoImageView = view.findViewById(R.id.favoritoCorazon)
        //celdaHoroscopo.setBackgroundColor(R.color.teal_200)
        //celdaHoroscopo.setBackgroundColor(Color.rgb(244,144,23))

    }

    fun render(horoscopo: Horoscopo){
        textView.setText(horoscopo.nombre)
        fechasTextView.setText(horoscopo.fecha)
        imagenImgView.setImageResource(horoscopo.logo)

        val context = itemView.context
        var esFavorito = SessionManager(context).esFavorito(horoscopo.id)

        if (esFavorito) {
            favoritoImageView.visibility = View.VISIBLE

        }else {
            favoritoImageView.visibility = View.GONE
        }
    }


    // Subraya el texto que coincide con la busqueda
    fun highlight(text: String) {
        try {
            val highlighted = textView.text.toString().highlight(text)
            textView.text = highlighted
        } catch (e: Exception) { }
        try {
            val highlighted = fechasTextView.text.toString().highlight(text)
            fechasTextView.text = highlighted
        } catch (e: Exception) { }
    }
    val imageUrl = "https://www.example.com/imagen.jpg"


    //ejemplo de extensión para el metodo String, que hace resaltar el texto buscado
    fun String.highlight(text: String) : SpannableString {
        val str = SpannableString(this)
        val startIndex = str.indexOf(text, 0, true)
        str.setSpan(BackgroundColorSpan(Color.rgb(244,144,255)), startIndex, startIndex + text.length, 0)
        return str
    }



}