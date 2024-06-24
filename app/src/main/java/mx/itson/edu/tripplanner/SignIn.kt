package mx.itson.edu.tripplanner

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SignIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in)

        val btnSignIn: Button = findViewById(R.id.btnSignIn)
        val btnIngresa: Button = findViewById(R.id.btnIngresa)

        btnSignIn.setOnClickListener {
            var intent: Intent = Intent(this, LogIn::class.java)
            startActivity(intent)
        }

        btnIngresa.setOnClickListener {
            var intent: Intent = Intent(this, LogIn::class.java)
            startActivity(intent)
        }

    }
}