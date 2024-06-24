package mx.itson.edu.tripplanner

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LogIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_log_in)

        var btnLogIn: Button = findViewById(R.id.btnLogIn) as Button
        var btnRegistrate: Button = findViewById(R.id.btnRegistrate) as Button

        btnLogIn.setOnClickListener {
            var intent: Intent = Intent(this, MisViajes::class.java)
            startActivity(intent)
        }

        btnRegistrate.setOnClickListener {
            var intent: Intent = Intent(this, SignIn::class.java)
            startActivity(intent)
        }

    }
}