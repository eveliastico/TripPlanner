package mx.itson.edu.tripplanner

import android.content.Intent
import android.os.Bundle
import android.widget.Button
<<<<<<< Updated upstream
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
=======
import android.widget.EditText
>>>>>>> Stashed changes

class LogIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_log_in)


        val btnLogIn: Button = findViewById(R.id.btnLogIn) as Button
        val btnRegistrate: Button = findViewById(R.id.btnRegistrate) as Button
        val etEmail: EditText = findViewById(R.id.etEmail)
        val etPassword: EditText = findViewById(R.id.etPassword)

        btnLogIn.setOnClickListener {
            if(validateInputs(etEmail, etPassword)) {
                val intent: Intent = Intent(this, MisViajes::class.java)
                startActivity(intent)
            }
        }

        btnRegistrate.setOnClickListener {
            val intent: Intent = Intent(this, SignIn::class.java)
            startActivity(intent)
        }

    }

    private fun validateInputs(etEmail: EditText, etPassword: EditText): Boolean{

        val email = etEmail.text.toString().trim()
        val password = etPassword.text.trim()

        if (email.isEmpty()){
            etEmail.error = "El correo electrónico no puede estar vacío"
            return false
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etEmail.error = "Ingrese un correo electrónico válido"
            return false
        }

        if (password.isEmpty()){
            etPassword.error = "La contraseña no puede estar vacía"
            return false
        }

        return true

    }

}