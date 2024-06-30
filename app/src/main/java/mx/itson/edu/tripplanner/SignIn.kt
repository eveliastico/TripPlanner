package mx.itson.edu.tripplanner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast

class SignIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val btnSignIn: Button = findViewById(R.id.btnSignIn) as Button
        val btnIngresa: Button = findViewById(R.id.btnIngresa) as Button
        val etNombre: EditText = findViewById(R.id.etNombre) as EditText
        val etTelefono: EditText = findViewById(R.id.etTelefono) as EditText
        val etEmail: EditText = findViewById(R.id.etEmail) as EditText
        val etPassword: EditText = findViewById(R.id.etPassword) as EditText
        val cbTerminos: CheckBox = findViewById(R.id.cbTerminos) as CheckBox


        btnSignIn.setOnClickListener {
            if (validateInputs(etNombre, etTelefono, etEmail, etPassword, cbTerminos)) {
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LogIn::class.java)

                startActivity(intent)
                finish()
            }
        }

        btnIngresa.setOnClickListener {
            var intent: Intent = Intent(this, LogIn::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun validateInputs( etNombre: EditText, etTelefono: EditText, etEmail: EditText, etPassword: EditText, cbTerminos: CheckBox): Boolean {

            val nombre = etNombre.text.toString().trim()
            val telefono = etTelefono.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString()

            if (nombre.isEmpty()) {
                etNombre.error = "El nombre no puede estar vacío"
                return false
            }

            if (telefono.isEmpty()) {
                etTelefono.error = "El teléfono no puede estar vacío"
                return false
            }

            if (!android.util.Patterns.PHONE.matcher(telefono).matches()) {
                etTelefono.error = "Ingrese un número de teléfono válido"
                return false
            }

            if (email.isEmpty()) {
                etEmail.error = "El correo electrónico no puede estar vacío"
                return false
            }

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                etEmail.error = "Ingrese un correo electrónico válido"
                return false
            }

            if (password.isEmpty()) {
                etPassword.error = "La contraseña no puede estar vacía"
                return false
            }

            if (!cbTerminos.isChecked) {
                Toast.makeText(this, "Debe aceptar los términos y condiciones", Toast.LENGTH_SHORT)
                    .show()
                return false
            }

            return true
        }
}