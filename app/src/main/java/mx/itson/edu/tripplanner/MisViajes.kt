package mx.itson.edu.tripplanner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class MisViajes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mis_viajes)

        var btnAddViaje: ImageButton = findViewById(R.id.btnAddViaje) as ImageButton

        btnAddViaje.setOnClickListener{
            var intent: Intent = Intent(this, NuevoViaje::class.java)
            startActivity(intent)
        }
    }
}