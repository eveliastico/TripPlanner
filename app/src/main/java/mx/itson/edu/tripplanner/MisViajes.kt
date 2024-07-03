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
import mx.itson.edu.tripplanner.databinding.ActivityMisViajesBinding

class MisViajes : AppCompatActivity() {

    lateinit var btnAddViaje: ImageButton
    private lateinit var binding: ActivityMisViajesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMisViajesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btnAddViaje = findViewById(R.id.btnAddViaje) as ImageButton

        btnAddViaje.setOnClickListener{
            var intent: Intent = Intent(this, NuevoViaje::class.java)
            startActivity(intent)
        }

        initRecyclerView()

    }
    fun initRecyclerView(){
        binding.recyclerMisViajes.layoutManager = LinearLayoutManager(this)
        binding.recyclerMisViajes.adapter =
            MisViajesAdapter(ViajesProvider.viajesList) {viaje ->
                onItemSelected(
                    viaje
                )
            }
    }

    fun onItemSelected(viaje: Viaje){
        Toast.makeText(this, viaje.destino, Toast.LENGTH_SHORT).show()
        navigateToDetallesViajeById(viaje)
    }

    private fun navigateToDetallesViajeById(viaje: Viaje){
        val intent = Intent(this, DetallesViaje::class.java).apply {
            putExtra("ID", viaje.id)
        }
        startActivity(intent)
    }
}