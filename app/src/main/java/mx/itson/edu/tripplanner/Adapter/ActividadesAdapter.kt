package mx.itson.edu.tripplanner.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mx.itson.edu.tripplanner.DataClass.Actividad
import mx.itson.edu.tripplanner.R

class ActividadesAdapter(private val actividadesList:List<Actividad>) : RecyclerView.Adapter<ActividadesViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActividadesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ActividadesViewHolder(layoutInflater.inflate(R.layout.item_actividad, parent, false))
    }

    override fun getItemCount(): Int {
        return actividadesList.size
    }

    //Este metodo lo que hace es pasar por cada uno de los items de la lista y llamara a la funcion
    // render de la clase ActividadesViewHolder
    override fun onBindViewHolder(holder: ActividadesViewHolder, position: Int) {
        val item = actividadesList[position]
        holder.render(item)
    }

}