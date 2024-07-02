package mx.itson.edu.tripplanner

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class LogIn : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        auth = FirebaseAuth.getInstance()

        try{
            FirebaseApp.initializeApp(this)
            Log.d("Firebase", "Firebase inicializado correctamente")

            val database = FirebaseDatabase.getInstance()
            val connectedRef = database.getReference(".info/connected")
            connectedRef.addValueEventListener(object: ValueEventListener {
                override fun onDataChange(snapshot : DataSnapshot) {
                    val connected = snapshot.getValue(Boolean::class.java) ?: false
                    if (connected) {
                        Log.d("Firebase", "Conectado a Firebase")
                        Toast.makeText(
                            applicationContext,
                            "Conectado a Firebase",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Log.d("Firebase", "Desconectado de Firebase")
                        Toast.makeText(
                            applicationContext,
                            "Desconectado de Firebase",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.w("Firebase", "Listener cancelado", error.toException())
                }
            })
        } catch(e : Exception){
            Log.e("Firebase", "Error la inicializar Firebase", e)
            Toast.makeText(this, "Error al inicializar Firebase ${e.message}", Toast.LENGTH_LONG).show()
        }

        val btnLogIn: Button = findViewById(R.id.btnLogIn) as Button
        val btnRegistrate: Button = findViewById(R.id.btnRegistrate) as Button
        val etEmail : EditText = findViewById(R.id.etEmail) as EditText
        val etPassword : EditText = findViewById(R.id.etPassword) as EditText

        btnLogIn.setOnClickListener {
            if (validateInputs(etEmail, etPassword)){
                loginUser(etEmail.text.toString(), etPassword.text.toString())
            }
        }

        btnRegistrate.setOnClickListener {
            val intent: Intent = Intent(this, SignIn::class.java)
            startActivity(intent)
        }
    }

    private fun loginUser(email: String, password: String){
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this){ task ->
            if (task.isSuccessful){
                Log.d("LogIn", "signInWithEmail:success")
                Toast.makeText(baseContext, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MisViajes::class.java)
                startActivity(intent)
                finish()
            } else {
                Log.w("LogIn", "SignInWithEmail:failure", task.exception)
                Toast.makeText(baseContext, "Autenticación fallida: ${task.exception?.message}",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateInputs(etEmail: EditText, etPassword: EditText): Boolean {

        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString()

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
        return true
    }
}