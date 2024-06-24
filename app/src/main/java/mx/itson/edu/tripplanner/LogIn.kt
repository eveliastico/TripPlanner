package mx.itson.edu.tripplanner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LogIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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