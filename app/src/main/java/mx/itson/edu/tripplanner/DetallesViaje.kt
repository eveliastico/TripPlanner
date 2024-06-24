package mx.itson.edu.tripplanner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
class DetallesViaje : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_viaje)

        val linearLayoutActivitiesContainer: LinearLayout = findViewById(R.id.linearLayoutActivitiesContainer)
        val btnAddActivity: Button = findViewById(R.id.btn_addActivity)

        btnAddActivity.setOnClickListener {
            addActivityField(linearLayoutActivitiesContainer)
        }
    }

    private fun addActivityField(container: LinearLayout) {
        // Crea un nuevo LinearLayout horizontal para la actividad y el precio
        val newActivityLayout = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(20, 5, 20, 5)
            }
        }

        // Crea un nuevo EditText para la actividad
        val newActivityEditText = EditText(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                2f
            )
            hint = "Actividad"
        }

        // Crea un nuevo EditText para el precio
        val newPriceEditText = EditText(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1f
            )
            hint = "Costo"
        }

        // Añade los EditTexts al nuevo LinearLayout
        newActivityLayout.addView(newActivityEditText)
        newActivityLayout.addView(newPriceEditText)

        // Añade el nuevo LinearLayout al contenedor
        container.addView(newActivityLayout)
    }


}