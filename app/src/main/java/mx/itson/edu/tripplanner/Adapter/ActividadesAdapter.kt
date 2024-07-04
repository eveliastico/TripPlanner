package mx.itson.edu.tripplanner.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mx.itson.edu.tripplanner.DataClass.Actividad
import mx.itson.edu.tripplanner.R

class ActividadesAdapter(
    private var actividades:List<Actividad> = emptyList(),
    private val onRemoveClick: (Actividad) -> Unit,
    private val onAddClick: () -> Unit
) : RecyclerView.Adapter<ActividadesViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActividadesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_actividad, parent, false)
        return ActividadesViewHolder(view, onRemoveClick, onAddClick)
    }

    override fun onBindViewHolder(holder: ActividadesViewHolder, position: Int) {
        holder.bind(actividades[position])
    }

    override fun getItemCount() = actividades.size

    fun updateActividades(newActividades: List<Actividad>) {
        actividades = newActividades
        notifyDataSetChanged()
    }

}