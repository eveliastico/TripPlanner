package mx.itson.edu.tripplanner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import mx.itson.edu.tripplanner.DataClass.Actividad
import mx.itson.edu.tripplanner.DataClass.Viaje
import java.time.LocalDate

class NuevoViaje : AppCompatActivity() {

    private lateinit var txtDestino: EditText
    private lateinit var txtActividades: EditText
    private lateinit var txtPresupuesto: EditText
    private lateinit var etDate: EditText
    private lateinit var btnGuardar: Button

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_viaje)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        txtDestino = findViewById(R.id.txtDestino)
        txtActividades = findViewById(R.id.txtActividades)
        txtPresupuesto = findViewById(R.id.txtPresupuesto)
        etDate = findViewById(R.id.etDate)
        btnGuardar = findViewById(R.id.btn_guardarViaje)

        etDate.setOnClickListener {
            showDatePickerDialog()
        }

        btnGuardar.setOnClickListener{
            guardarViaje()
        }

    }

    private fun guardarViaje(){
        val destino = txtDestino.text.toString().trim()
        val actividadesText = txtActividades.text.toString().trim()
        val presupuesto = txtPresupuesto.text.toString().toFloatOrNull() ?: 0f
        val fecha = etDate.text.toString().trim()

        if (destino.isEmpty() || fecha.isEmpty() || actividadesText.isEmpty()){
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        val userId = auth.currentUser?.uid
        if (userId == null){
            Toast.makeText(this, "Error: Usuario no autenticado", Toast.LENGTH_SHORT).show()
            return
        }

        val actividades = parseActividades(actividadesText)
        if (actividades.isEmpty()){
            Toast.makeText(this, "Por favor, ingresa al menos una actividad con su costo", Toast.LENGTH_SHORT).show()
            return
        }

        val viajeRef = database.getReference("viajes").push()
        val viajeId = viajeRef.key ?: return

        val viaje = Viaje(
            id = viajeId,
            userId = userId,
            destino = destino,
            actividades = actividades,
            presupuestoEstimado = presupuesto,
            fechaInicio = fecha
        )

        viajeRef.setValue(viaje).addOnCompleteListener{ task ->
            if (task.isSuccessful){
                Toast.makeText(this, "Viaje guardado exitosamente", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MisViajes::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Error al guardar el viaje", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun parseActividades(actividadesText: String): List<Actividad> {
        return actividadesText.split(",").mapNotNull { actividadText ->
            val parts = actividadText.trim().split(":")
            if (parts.size == 2){
                val nombre = parts[0].trim()
                val costo = parts[1].trim().toFloatOrNull()
                if (nombre.isNotEmpty() && costo != null){
                    Actividad(nombre, costo)
                } else null
            } else null
        }
    }

    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(supportFragmentManager, "datePicker")
    }

    private fun onDateSelected(day: Int, month: Int, year: Int) {
        etDate.setText("$day/$month/$year")
    }
}