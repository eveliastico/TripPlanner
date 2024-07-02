package mx.itson.edu.tripplanner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.itson.edu.tripplanner.Adapter.ActividadesAdapter
import mx.itson.edu.tripplanner.DataProvider.ActividadesProvider
import mx.itson.edu.tripplanner.Utilities.CustomCircleDrawable
import mx.itson.edu.tripplanner.databinding.ActivityDetallesViajeBinding

class DetallesViaje : AppCompatActivity() {

    private lateinit var binding: ActivityDetallesViajeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Esta linea ya no se necesita gracias al Binding
        //setContentView(R.layout.activity_detalles_viaje)
        binding = ActivityDetallesViajeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        initGraphs()
    }

    fun initRecyclerView(){
        //Ya no se necesita esta linea tampoco
        //val recyclerView = findViewById<RecyclerView>(R.id.recyclerDetallesViajes)
        //Ahora solo se llama al Binding
        binding.recyclerDetallesViajes.layoutManager = LinearLayoutManager(this)
        binding.recyclerDetallesViajes.adapter = ActividadesAdapter(ActividadesProvider.actividadesPlaneadasList)
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