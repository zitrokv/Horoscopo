package com.example.horoscopo

import android.content.res.Resources
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HoroscopoAdapter (private val dataSet: List<Horoscopo>) :
RecyclerView.Adapter<ViewHolder<Any?>>() {

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
        viewHolder.textView.setText(dataSet[position].nombre)
        viewHolder.fechasTextView.setText(dataSet[position].fecha)
        viewHolder.imagenImgView.setImageResource(dataSet[position].logo)

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size


}

class ViewHolder<Bitmap>(view: View) : RecyclerView.ViewHolder(view) {
    val textView: TextView
    val fechasTextView: TextView
    val imagenImgView :ImageView

    init {
        // Define click listener for the ViewHolder's View
        textView = view.findViewById(R.id.nombreHoroscopoTextView)
        imagenImgView = view.findViewById<ImageView?>(R.id.imagenImgView)
        fechasTextView = view.findViewById(R.id.fechasTextView)
    }

    val imageUrl = "https://www.example.com/imagen.jpg"





}