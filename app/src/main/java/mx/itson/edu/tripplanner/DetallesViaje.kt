package mx.itson.edu.tripplanner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.itson.edu.tripplanner.Adapter.ActividadesAdapter
import mx.itson.edu.tripplanner.DataProvider.ActividadesProvider

class DetallesViaje : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_viaje)
        initRecyclerView()
    }

    fun initRecyclerView(){
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerDetallesViajes)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ActividadesAdapter(ActividadesProvider.actividadesPlaneadasList)
    }


}