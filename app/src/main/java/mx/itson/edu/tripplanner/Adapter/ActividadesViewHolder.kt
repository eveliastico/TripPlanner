package mx.itson.edu.tripplanner.Adapter

import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.itson.edu.tripplanner.DataClass.Actividad
import mx.itson.edu.tripplanner.R
import mx.itson.edu.tripplanner.databinding.ItemActividadBinding
import org.w3c.dom.Text

class ActividadesViewHolder(
    view: View,
    private val onRemoveClick: (Actividad) -> Unit,
    private val onAddClick: () -> Unit
):RecyclerView.ViewHolder(view) {

    private val txtActividad: TextView = view.findViewById(R.id.txtActividad)
    private val txtCosto: TextView = view.findViewById(R.id.txtCosto)
    private val btnRemove: ImageButton = view.findViewById(R.id.btnRemoveActividad)
    private val btnAdd: ImageButton = view.findViewById(R.id.btnAddActividad)

    fun bind(actividad: Actividad) {
        txtActividad.text = actividad.nombre
        txtCosto.text = "$${actividad.costo}"
        btnRemove.setOnClickListener{onRemoveClick(actividad)}
        btnAdd.setOnClickListener{onAddClick()}
    }

}