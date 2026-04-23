package my.edu.uthm.academiccare

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth // Changed this
import com.google.firebase.firestore.firestore // Changed this
import com.google.firebase.firestore.ktx.firestore

class SignUp : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private val db = Firebase.firestore

    // Move these to class level so performSignUp can see them
    private lateinit var enterFullName: EditText
    private lateinit var enterMatric: EditText
    private lateinit var enterEmail: EditText
    private lateinit var enterPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        auth = Firebase.auth
        // Initialize the class-level variables
        val buttonRegister = findViewById<Button>(R.id.btnRegister)
        enterFullName = findViewById(R.id.etFullName)
        enterMatric = findViewById(R.id.etMatric)
        enterEmail = findViewById(R.id.etEmail)
        enterPassword = findViewById(R.id.etPassword)

        buttonRegister.setOnClickListener {
            performSignUp()
        }
    }

    private fun performSignUp() {
        // Use the correct variable names (enterFullName, etc.)
        val name = enterFullName.text.toString().trim()
        val matric = enterMatric.text.toString().trim()
        val email = enterEmail.text.toString().trim()
        val password = enterPassword.text.toString().trim()

        if (name.isEmpty() || matric.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        if (!email.endsWith("@student.uthm.edu.my")) {
            enterEmail.error = "Use your official UTHM student email" // Fixed variable name
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userId = auth.currentUser?.uid
                    val userMap = hashMapOf(
                        "fullName" to name,
                        "matricNo" to matric,
                        "email" to email,
                        "role" to "student"
                    )

                    userId?.let {
                        db.collection("users").document(it).set(userMap)
                            .addOnSuccessListener {
                                Toast.makeText(this, "Welcome to AcademicCare!", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this, SignUp::class.java)
                                startActivity(intent)
                                finish()
                            }
                    }
                } else {
                    Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }
    }
}