package mx.itson.edu.tripplanner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import mx.itson.edu.tripplanner.Adapter.ActividadesAdapter
import mx.itson.edu.tripplanner.DataClass.Viaje
import mx.itson.edu.tripplanner.Utilities.CustomCircleDrawable
import mx.itson.edu.tripplanner.databinding.ActivityDetallesViajeBinding
import java.text.SimpleDateFormat
import java.util.Locale

class DetallesViaje : AppCompatActivity() {

    private lateinit var binding: ActivityDetallesViajeBinding
    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private lateinit var actividadesAdapter: ActividadesAdapter
    private var viajeId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetallesViajeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference

        viajeId = intent.getStringExtra("ID")
        if (viajeId == null){
            Toast.makeText(this, "Error: ID de viaje no encontrado", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        initRecyclerView()
        initGraphs()
        loadViajeDetails()
    }

    fun initRecyclerView(){
        binding.recyclerDetallesViajes.layoutManager = LinearLayoutManager(this)
        actividadesAdapter = ActividadesAdapter(emptyList())
        binding.recyclerDetallesViajes.adapter = actividadesAdapter
    }

    private fun initGraphs(){
        binding.graphAlojamiento.background =  CustomCircleDrawable(this, 64f, R.color.colorAlojamiento)
        binding.graphTransporte.background = CustomCircleDrawable(this, 40f, R.color.colorTransporte)
        binding.graphComidas.background = CustomCircleDrawable(this, 90f, R.color.colorComidas)
    }

    private fun loadViajeDetails(){
        database.child("viajes").child(viajeId!!).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val viaje = snapshot.getValue(Viaje::class.java)
                viaje?.let {
                    updateUI(it)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@DetallesViaje, "Error al cargar datos: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun updateUI(viaje: Viaje) {
        binding.txtDestino.text = viaje.destino
        binding.txtFecha.text = viaje.fechaInicio
        actividadesAdapter.updateActividades(viaje.actividades)

        // Calcular días restantes
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val fechaInicio = sdf.parse(viaje.fechaInicio)
        val diasRestantes = ((fechaInicio?.time ?: 0) - System.currentTimeMillis()) / (1000 * 60 * 60 * 24)
        binding.diasRestantes.text = diasRestantes.toInt().toString()
    }



}