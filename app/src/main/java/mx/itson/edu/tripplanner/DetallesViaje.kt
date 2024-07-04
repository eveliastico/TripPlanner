package mx.itson.edu.tripplanner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import mx.itson.edu.tripplanner.Adapter.ActividadesAdapter
import mx.itson.edu.tripplanner.DataClass.Actividad
import mx.itson.edu.tripplanner.DataClass.Viaje
import mx.itson.edu.tripplanner.Utilities.CustomCircleDrawable
import mx.itson.edu.tripplanner.databinding.ActivityDetallesViajeBinding
import java.text.SimpleDateFormat
import java.util.Calendar
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
        if (viajeId == null) {
            Toast.makeText(this, "Error: ID de viaje no encontrado", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        initRecyclerView()
        initGraphs()
        loadViajeDetails()
    }

    private fun initRecyclerView() {
        binding.recyclerDetallesViajes.layoutManager = LinearLayoutManager(this)
        actividadesAdapter = ActividadesAdapter(
            emptyList(),
            onRemoveClick = { actividad -> showRemoveActividadDialog(actividad) },
            onAddClick = { showAddActividadDialog() }
        )
        binding.recyclerDetallesViajes.adapter = actividadesAdapter
    }

    private fun initGraphs() {
        binding.graphAlojamiento.background = CustomCircleDrawable(this, 64f, R.color.colorAlojamiento)
        binding.graphTransporte.background = CustomCircleDrawable(this, 40f, R.color.colorTransporte)
        binding.graphComidas.background = CustomCircleDrawable(this, 90f, R.color.colorComidas)
    }

    private fun loadViajeDetails() {
        database.child("viajes").child(viajeId!!).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val viaje = snapshot.getValue(Viaje::class.java)
                viaje?.let {
                    runOnUiThread {
                        updateUI(it)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                runOnUiThread {
                    Toast.makeText(
                        this@DetallesViaje,
                        "Error al cargar datos: ${error.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

    private fun showAddActividadDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_actividad, null)
        val etActividad = dialogView.findViewById<EditText>(R.id.etActividad)

        AlertDialog.Builder(this)
            .setTitle("Agregar Actividad")
            .setView(dialogView)
            .setPositiveButton("Agregar") { _, _ ->
                val input = etActividad.text.toString()
                addNewActividad(input)
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun addNewActividad(input: String) {
        val parts = input.split(":")
        if (parts.size == 2) {
            val nombre = parts[0].trim()
            val costo = parts[1].trim().toFloatOrNull() ?: 0f
            if (nombre.isNotEmpty()) {
                val nuevaActividad = Actividad(nombre = nombre, costo = costo)
                database.child("viajes").child(viajeId!!).child("actividades")
                    .push().setValue(nuevaActividad)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Actividad agregada", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(
                            this,
                            "Error al agregar la actividad",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            } else {
                Toast.makeText(this, "Formato inválido", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Formato inválido", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showRemoveActividadDialog(actividad: Actividad) {
        AlertDialog.Builder(this)
            .setTitle("Eliminar Actividad")
            .setMessage("¿Estás seguro de que quieres eliminar esta actividad?")
            .setPositiveButton("Sí") { _, _ ->
                removeActividad(actividad)
            }
            .setNegativeButton("No", null)
            .show()
    }

    private fun removeActividad(actividad: Actividad) {
        database.child("viajes").child(viajeId!!).child("actividades")
            .orderByChild("nombre").equalTo(actividad.nombre)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (childSnapshot in snapshot.children) {
                        childSnapshot.ref.removeValue()
                            .addOnSuccessListener {
                                Toast.makeText(this@DetallesViaje, "Actividad eliminada", Toast.LENGTH_SHORT).show()
                            }
                            .addOnFailureListener {
                                Toast.makeText(this@DetallesViaje, "Error al eliminar la actividad", Toast.LENGTH_SHORT).show()
                            }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@DetallesViaje, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun updateUI(viaje: Viaje) {
        binding.txtDestino.text = viaje.destino ?: ""
        binding.txtFecha.text = viaje.fechaInicio ?: ""
        actividadesAdapter.updateActividades(viaje.actividades ?: emptyList())

        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val fechaInicio = viaje.fechaInicio?.let {
            sdf.parse(it)
        }

        if (fechaInicio != null) {
            val today = Calendar.getInstance()
            val startDate = Calendar.getInstance().apply { time = fechaInicio }

            val diffInMillis = startDate.timeInMillis - today.timeInMillis
            val diffInDays = (diffInMillis / (1000 * 60 * 60 * 24)).toInt()

            val daysCorrected = diffInDays
            binding.diasRestantes.text = daysCorrected.toString()
        }
    }
}