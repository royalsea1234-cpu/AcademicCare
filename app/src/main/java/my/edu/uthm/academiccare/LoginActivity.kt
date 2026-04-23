package my.edu.uthm.academiccare

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlin.jvm.java

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        auth = Firebase.auth

        val buttonLogin = findViewById<Button>(R.id.btnLogin)
        val enterEmail = findViewById<EditText>(R.id.etEmail)
        val enterPassword = findViewById<EditText>(R.id.etPassword)
        val goToSignUp = findViewById<TextView>(R.id.tvGoToSignUp)

        buttonLogin.setOnClickListener {
            val email = enterEmail.text.toString()
            val password = enterPassword.text.toString()

            // We will add the Firebase signInWithEmailAndPassword here next!
        }

        goToSignUp.setOnClickListener {
            // This will navigate to your SignUpActivity
             val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

    }
}