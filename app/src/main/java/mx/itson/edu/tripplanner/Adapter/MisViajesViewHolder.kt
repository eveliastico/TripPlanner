package mx.itson.edu.tripplanner.Adapter

import android.content.Intent
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import mx.itson.edu.tripplanner.DataClass.Viaje
import mx.itson.edu.tripplanner.DetallesViaje
import mx.itson.edu.tripplanner.NuevoViaje
import mx.itson.edu.tripplanner.R

class MisViajesViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val destino = view.findViewById<TextView>(R.id.txtDestinoMiViaje)
    val fecha = view.findViewById<TextView>(R.id.txtFecha)
    val detalles = view.findViewById<ImageButton>(R.id.btnDetalles)

    fun render(viajeModel: Viaje, onClickListener:(Viaje)-> Unit){
        fecha.text = viajeModel.fechaInicio.toString()
        destino.text = viajeModel.destino

        itemView.setOnClickListener{onClickListener(viajeModel)}

    }
}