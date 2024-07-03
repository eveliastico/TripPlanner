package mx.itson.edu.tripplanner.Adapter

import android.content.Context
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
import mx.itson.edu.tripplanner.databinding.ItemMisViajesBinding

class MisViajesViewHolder(view: View, val context: Context): RecyclerView.ViewHolder(view) {

    val binding = ItemMisViajesBinding.bind(view)

    fun render(viajeModel: Viaje, onClickListener:(Viaje)-> Unit){
        binding.txtFecha.text = viajeModel.fechaInicio.toString()
        binding.txtDestinoMiViaje.text = viajeModel.destino

        itemView.setOnClickListener{

            onClickListener(viajeModel)

        }

    }
}