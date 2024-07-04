package mx.itson.edu.tripplanner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import mx.itson.edu.tripplanner.Adapter.ActividadesAdapter
import mx.itson.edu.tripplanner.Adapter.MisViajesAdapter
import mx.itson.edu.tripplanner.DataClass.Viaje
import mx.itson.edu.tripplanner.databinding.ActivityMisViajesBinding

class MisViajes : AppCompatActivity() {

    lateinit var btnAddViaje: ImageButton
    private lateinit var binding: ActivityMisViajesBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var viajesAdapter: MisViajesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMisViajesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        btnAddViaje = findViewById(R.id.btnAddViaje) as ImageButton

        binding.btnAddViaje.setOnClickListener{
            val intent = Intent(this, NuevoViaje::class.java)
            startActivity(intent)
        }

        initRecyclerView()
        loadViajes()

    }

    fun initRecyclerView(){
        binding.recyclerMisViajes.layoutManager = LinearLayoutManager(this)
        viajesAdapter = MisViajesAdapter(emptyList()) { viaje ->
            onItemSelected(viaje)
        }
        binding.recyclerMisViajes.adapter = viajesAdapter
    }

    private fun loadViajes(){
        val userId = auth.currentUser?.uid ?: return
        val viajesRef = database.getReference("viajes").orderByChild("userId").equalTo(userId)

        viajesRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val viajes = mutableListOf<Viaje>()
                for (viajeSnapshot in snapshot.children){
                    val viaje = viajeSnapshot.getValue(Viaje::class.java)
                    viaje?.let {
                        it.id = viajeSnapshot.key ?: ""
                        viajes.add(it)
                    }
                }
                viajesAdapter.updateViajes(viajes)
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MisViajes, "Error al cargar viajes: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun onItemSelected(viaje: Viaje){
        val intent = Intent(this, DetallesViaje::class.java).apply {
            putExtra("ID", viaje.id)
        }
        startActivity(intent)
    }

    private fun navigateToDetallesViajeById(viaje: Viaje){
        val intent = Intent(this, DetallesViaje::class.java).apply {
            putExtra("ID", viaje.id)
        }
        startActivity(intent)
    }
}