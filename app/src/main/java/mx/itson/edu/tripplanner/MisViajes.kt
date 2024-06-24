package mx.itson.edu.tripplanner

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MisViajes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mis_viajes)

        var btnAddViaje: ImageButton = findViewById(R.id.btnAddViaje) as ImageButton

        btnAddViaje.setOnClickListener{
            var intent: Intent = Intent(this, NuevoViaje::class.java)
            startActivity(intent)
        }

    }
}