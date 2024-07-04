package mx.itson.edu.tripplanner.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mx.itson.edu.tripplanner.DataClass.Actividad
import mx.itson.edu.tripplanner.databinding.ItemNuevaActividadBinding

class NuevaActividadViewHolder(view: View, private val adapter: NuevaActividadAdapter) : RecyclerView.ViewHolder(view) {

    val binding = ItemNuevaActividadBinding.bind(view)

    init {
        binding.btnAddActividad.setOnClickListener {
            val nuevaActividad = Actividad("", 0f) // Nueva instancia vacía de Actividad
            adapter.addActividad(nuevaActividad)
        }

        binding.btnRemoveActividad.setOnClickListener {
            adapter.removeActividad(adapterPosition)
        }
    }

    fun render(actividadModel: Actividad){
        binding.txtActividad.setText(actividadModel.nombre)
        binding.txtCosto.setText(actividadModel.costo.toString())
    }
}