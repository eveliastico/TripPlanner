package mx.itson.edu.tripplanner.Adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.itson.edu.tripplanner.DataClass.Actividad
import mx.itson.edu.tripplanner.R
import mx.itson.edu.tripplanner.databinding.ItemActividadBinding

class ActividadesViewHolder(view: View):RecyclerView.ViewHolder(view) {

    val binding = ItemActividadBinding.bind(view)

    //Ya no se ocupa crear todos estos valores por q solo se llama al binding.
    //val actividad = view.findViewById<TextView>(R.id.txtActividad)
    //val costo = view.findViewById<TextView>(R.id.txtCosto)

    fun render(actvidadModel: Actividad){
        binding.txtActividad.text = actvidadModel.nombre
        binding.txtCosto.text = actvidadModel.costo.toString()
    }

}