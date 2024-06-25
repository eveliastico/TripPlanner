package mx.itson.edu.tripplanner.Adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.itson.edu.tripplanner.DataClass.Actividad
import mx.itson.edu.tripplanner.R

class ActividadesViewHolder(view: View):RecyclerView.ViewHolder(view) {

    val actividad = view.findViewById<TextView>(R.id.txtActividad)
    val costo = view.findViewById<TextView>(R.id.txtCosto)

    fun render(actvidadModel: Actividad){
        actividad.text = actvidadModel.nombre
        costo.text = actvidadModel.costo.toString()
    }

}