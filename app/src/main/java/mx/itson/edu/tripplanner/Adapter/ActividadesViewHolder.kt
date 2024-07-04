package mx.itson.edu.tripplanner.Adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.itson.edu.tripplanner.DataClass.Actividad
import mx.itson.edu.tripplanner.R
import mx.itson.edu.tripplanner.databinding.ItemActividadBinding
import org.w3c.dom.Text

class ActividadesViewHolder(view: View):RecyclerView.ViewHolder(view) {

    private val actividad = view.findViewById<TextView>(R.id.txtActividad)
    private val costo = view.findViewById<TextView>(R.id.txtCosto)

    fun render(actividadModel: Actividad) {
        actividad.text = actividadModel.nombre
        costo.text = actividadModel.costo.toString()
    }

}