package mx.itson.edu.tripplanner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.FirebaseDatabase

class SignIn : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        val btnSignIn: Button = findViewById(R.id.btnSignIn) as Button
        val btnIngresa: Button = findViewById(R.id.btnIngresa) as Button
        val etNombre: EditText = findViewById(R.id.etNombre) as EditText
        val etTelefono: EditText = findViewById(R.id.etTelefono) as EditText
        val etEmail: EditText = findViewById(R.id.etEmail) as EditText
        val etPassword: EditText = findViewById(R.id.etPassword) as EditText
        val cbTerminos: CheckBox = findViewById(R.id.cbTerminos) as CheckBox


        btnSignIn.setOnClickListener {
            val nombre = etNombre.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            val telefono = etTelefono.text.toString()
            val terminosAceptados = cbTerminos.isChecked

            if (validateInputs(nombre, telefono, email, password, terminosAceptados)){
                registerUser(nombre, email, password, telefono)
            }
        }

        btnIngresa.setOnClickListener {
            var intent: Intent = Intent(this, LogIn::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun registerUser(name: String, email: String, password: String, phone: String) {

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this){ task ->

            if (task.isSuccessful){
                val user = auth.currentUser
                user?.let {
                    saverUserToDatabase(it.uid, name, email, phone)
                }
            } else {
                val errorMessage = task.exception?.message ?: "Error desconocido"
                Toast.makeText(baseContext, "Authentication Failed $errorMessage", Toast.LENGTH_SHORT).show()

            }

        }

    }

    private fun saverUserToDatabase(userId: String, name: String, email: String, phone: String){
        val userRef = database.getReference("users").child(userId)
        val userData = HashMap<String, Any>()
        userData["name"] = name
        userData["email"] = email
        userData["phone"] = phone

        userRef.setValue(userData).addOnSuccessListener {
            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LogIn::class.java)
            startActivity(intent)
            finish()
        }
            .addOnFailureListener{ e ->
                val errorMessage = e.message ?: "Error desconocido"
                Toast.makeText(this, "Error al guardar datos: $errorMessage", Toast.LENGTH_SHORT).show()
            }
    }

    private fun validateInputs(nombre: String, telefono: String, email: String, password: String, terminosAceptados: Boolean): Boolean{
        if (nombre.isEmpty() || telefono.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
            return false
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Email inválido", Toast.LENGTH_SHORT).show()
            return false
        }
        if (password.length < 6) {
            Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show()
            return false
        }
        if (!terminosAceptados) {
            Toast.makeText(this, "Debes aceptar los términos y condiciones", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

}