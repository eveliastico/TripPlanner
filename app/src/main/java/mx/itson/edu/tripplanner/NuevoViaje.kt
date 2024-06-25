package mx.itson.edu.tripplanner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.ImageButton
import java.time.LocalDate

class NuevoViaje : AppCompatActivity() {

    lateinit var etDate: EditText
    lateinit var btnGuardar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_viaje)

        etDate = findViewById(R.id.etDate)

        etDate.setOnClickListener {
            showDatePickerDialog()
        }

        btnGuardar= findViewById(R.id.btn_guardarViaje)

        btnGuardar.setOnClickListener{
            var intent: Intent = Intent(this, DetallesViaje::class.java)
            startActivity(intent)
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