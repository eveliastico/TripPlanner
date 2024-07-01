package mx.itson.edu.tripplanner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.itson.edu.tripplanner.Adapter.ActividadesAdapter
import mx.itson.edu.tripplanner.Adapter.MisViajesAdapter
import mx.itson.edu.tripplanner.DataClass.Viaje
import mx.itson.edu.tripplanner.DataProvider.ActividadesProvider
import mx.itson.edu.tripplanner.DataProvider.ViajesProvider

class MisViajes : AppCompatActivity() {

    lateinit var btnAddViaje: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mis_viajes)

        btnAddViaje = findViewById(R.id.btnAddViaje) as ImageButton

        btnAddViaje.setOnClickListener{
            var intent: Intent = Intent(this, NuevoViaje::class.java)
            startActivity(intent)
        }

        initRecyclerView()

    }
    fun initRecyclerView(){
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerMisViajes)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MisViajesAdapter(ViajesProvider.viajesList) {viaje ->
            onItemSelected(
                viaje
            )
        }
    }

    fun onItemSelected(viaje: Viaje){
        Toast.makeText(this, viaje.destino, Toast.LENGTH_SHORT).show()
    }
}