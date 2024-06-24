package mx.itson.edu.tripplanner

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class NuevoViaje : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_viaje)

        val btnGuardarViaje : Button = findViewById(R.id.btnGuardarViaje) as Button

        btnGuardarViaje.setOnClickListener {
            var intent: Intent = Intent(this, DetallesViaje::class.java)
            startActivity(intent)
        }

    }
}