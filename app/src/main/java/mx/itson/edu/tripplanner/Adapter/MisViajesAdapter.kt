package mx.itson.edu.tripplanner.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mx.itson.edu.tripplanner.DataClass.Viaje
import mx.itson.edu.tripplanner.R

class MisViajesAdapter(private val viajesList:List<Viaje>, private val onClickListener:(Viaje)-> Unit ) : RecyclerView.Adapter<MisViajesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MisViajesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MisViajesViewHolder(layoutInflater.inflate(R.layout.item_mis_viajes, parent, false), parent.context)
    }

    override fun getItemCount(): Int {
        return viajesList.size
    }

    //Este metodo lo que hace es pasar por cada uno de los items de la lista y llamara a la funcion
    // render de la clase MisViajesViewHolder
    override fun onBindViewHolder(holder: MisViajesViewHolder, position: Int) {
        val item = viajesList[position]
        holder.render(item, onClickListener)
    }
}