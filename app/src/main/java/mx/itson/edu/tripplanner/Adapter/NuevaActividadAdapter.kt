package mx.itson.edu.tripplanner.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mx.itson.edu.tripplanner.DataClass.Actividad
import mx.itson.edu.tripplanner.R

class NuevaActividadAdapter(private val listaActividades: List<Actividad>) : RecyclerView.Adapter<NuevaActividadViewHolder>() {

    val actividadesList: MutableList<Actividad> = listaActividades.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NuevaActividadViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return NuevaActividadViewHolder(layoutInflater.inflate(R.layout.item_nueva_actividad, parent, false), this)
    }

    override fun getItemCount(): Int {
        return actividadesList.size
    }

    override fun onBindViewHolder(holder: NuevaActividadViewHolder, position: Int) {
        val item = actividadesList[position]
        holder.render(item)
    }

    // Método para agregar un nuevo item
    fun addActividad(actividad: Actividad) {
        actividadesList.add(actividad)
        notifyItemInserted(actividadesList.size - 1)
    }

    // Método para eliminar un item en una posición específica
    fun removeActividad(position: Int) {
        if (position >= 0 && position < actividadesList.size) {
            actividadesList.removeAt(position)
            notifyItemRemoved(position)
        }
    }
}