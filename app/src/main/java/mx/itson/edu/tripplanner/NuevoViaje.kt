package mx.itson.edu.tripplanner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import mx.itson.edu.tripplanner.Adapter.MisViajesAdapter
import mx.itson.edu.tripplanner.Adapter.NuevaActividadAdapter
import mx.itson.edu.tripplanner.DataProvider.ActividadesProvider
import mx.itson.edu.tripplanner.DataProvider.ViajesProvider
import mx.itson.edu.tripplanner.databinding.ActivityNuevoViajeBinding
import java.time.LocalDate

class NuevoViaje : AppCompatActivity() {

    private lateinit var binding: ActivityNuevoViajeBinding

//    lateinit var etDate: EditText
//    lateinit var btnGuardar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNuevoViajeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGuardarViaje.setOnClickListener{
            var intent: Intent = Intent(this, DetallesViaje::class.java)
            startActivity(intent)
        }

    }

    fun initRecyclerView(){
        binding.recyclerActividades.layoutManager = LinearLayoutManager(this)
        binding.recyclerActividades.adapter =
            NuevaActividadAdapter(ActividadesProvider.actividadesPlaneadasList)
    }
}