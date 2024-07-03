package mx.itson.edu.tripplanner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.itson.edu.tripplanner.Adapter.ActividadesAdapter
import mx.itson.edu.tripplanner.DataClass.Viaje
import mx.itson.edu.tripplanner.DataProvider.ActividadesProvider
import mx.itson.edu.tripplanner.DataProvider.ViajesProvider
import mx.itson.edu.tripplanner.Utilities.CustomCircleDrawable
import mx.itson.edu.tripplanner.databinding.ActivityDetallesViajeBinding

class DetallesViaje : AppCompatActivity() {

    private lateinit var binding: ActivityDetallesViajeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetallesViajeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        initGraphs()

        val idViaje = intent.getLongExtra("ID", -1)

        for (viaje in ViajesProvider.viajesList){
            if(viaje.id == idViaje){
                binding.txtDestino.text = viaje.destino
                binding.txtFecha.text = viaje.fechaInicio.toString()
                break
            }
        }

    }

    fun initRecyclerView(){
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerDetallesViajes)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ActividadesAdapter(ActividadesProvider.actividadesPlaneadasList)
    }

    private fun initGraphs(){
        val graphAlojamiento = findViewById<ImageView>(R.id.graphAlojamiento)
        val graphTransporte = findViewById<ImageView>(R.id.graphTransporte)
        val graphComidas = findViewById<ImageView>(R.id.graphComidas)

        graphAlojamiento.background =  CustomCircleDrawable(this, 64f, R.color.colorAlojamiento)
        graphTransporte.background = CustomCircleDrawable(this, 40f, R.color.colorTransporte)
        graphComidas.background = CustomCircleDrawable(this, 90f, R.color.colorComidas)
    }


}