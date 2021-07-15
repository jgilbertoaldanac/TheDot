package aldana.jesus.thedot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class login : AppCompatActivity() {

    var auth = FirebaseAuth.getInstance()
    private val db= FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val btn_login: Button = findViewById(R.id.btn_lg_iniciarSesion) as Button
        val btn_back: ImageButton = findViewById(R.id.btn_lg_back) as ImageButton


        btn_login.setOnClickListener{
            valida_ingreso()
        }

        btn_back.setOnClickListener {
            var intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun valida_ingreso() {
        val et_correo: EditText = findViewById(R.id.et_correo)
        val et_contra: EditText = findViewById(R.id.et_contrasenia)

        var correo: String = et_correo.text.toString()
        var contra: String = et_contra.text.toString()

        if(!correo.isNullOrBlank() && !contra.isNullOrBlank()){
            ingresarFirebase(correo,contra)
        }else{
            Toast.makeText(this, "Ingrese los datos",
                Toast.LENGTH_SHORT).show()
        }
    }

    private fun ingresarFirebase(email:String, password:String){
        db.collection("usuarios").document(email).get().addOnSuccessListener { document ->
            if(document.exists()){
                var email_bd = document.getString("correo")
                var password_bd = document.getString("contrasenia")

                if(email.equals(email_bd) && password.equals(password_bd)){
                    val intent:Intent = Intent(this,HomeActivity::class.java)
                    intent.putExtra("nombre",email)
                    startActivity(intent)
                }else{
                    Toast.makeText(baseContext, "Autenticación fallida.", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(
                    baseContext, "No esta registrado ese usuario", Toast.LENGTH_SHORT).show()
            }

        }

        
       // auth.signInWithEmailAndPassword(email, password)
         //   .addOnCompleteListener(this) { task ->
           //     if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    // Log.d(TAG, "signInWithEmail:success")
             //       val user = auth.currentUser
                    //updateUI(user)
               //     val intent:Intent = Intent(this,HomeActivity::class.java)
                 //   startActivity(intent)
               // } else {
                    // If sign in fails, display a message to the user.
                    //Log.w(TAG, "signInWithEmail:failure", task.exception)
                 //   Toast.makeText(
                   //     baseContext, "Autenticación fallida.",
                     //   Toast.LENGTH_SHORT
                   // ).show()
                    //updateUI(null)
               // }
           // }
    }
}


