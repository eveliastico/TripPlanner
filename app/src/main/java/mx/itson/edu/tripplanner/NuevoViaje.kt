package mx.itson.edu.tripplanner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import java.time.LocalDate

class NuevoViaje : AppCompatActivity() {

    private val viajesList = mutableListOf<Viaje>()
    private lateinit var txtDestino: EditText
    private lateinit var txtPresupuesto: EditText
    private lateinit var calendarView: CalendarView
    private lateinit var btnGuardarViaje: Button
    private var fechaInicio: LocalDate? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_viaje)

        txtDestino = findViewById(R.id.txtDestino)
        txtPresupuesto = findViewById(R.id.txtPresupuesto)
        calendarView = findViewById(R.id.calendarView)
        btnGuardarViaje = findViewById(R.id.btn_guardarViaje)

        // Obtener la fecha seleccionada del CalendarView
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            fechaInicio = LocalDate.of(year, month + 1, dayOfMonth)
        }

        btnGuardarViaje.setOnClickListener {
            guardarViaje()
        }
    }

    private fun guardarViaje() {
        val destino = txtDestino.text.toString()
        val presupuestoEstimado = txtPresupuesto.text.toString().toFloatOrNull() ?: 0.0f

        val actividades = listOf(
            Actividad("Ejemplo de actividad", 100.0f)
        )

        if (destino.isNotEmpty() && fechaInicio != null) {
            val nuevoViaje = Viaje(
                destino = destino,
                actividades = actividades,
                presupuestoEstimado = presupuestoEstimado,
                fechaInicio = fechaInicio!!
            )

            viajesList.add(nuevoViaje)
        } else {

        }
    }
}