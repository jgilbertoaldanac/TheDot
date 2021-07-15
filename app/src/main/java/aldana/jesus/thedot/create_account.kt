package aldana.jesus.thedot

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class create_account : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private val db= FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        auth = Firebase.auth

        val btn_create_account: Button = findViewById(R.id.btn_ca_createAccount) as Button
        val btn_return: ImageButton = findViewById(R.id.btn_ca_return) as ImageButton

        btn_create_account.setOnClickListener{
            //Poner una variable booleana para validar si se hizo el registro o no y que retorne
            valida_registro()
            //Si retorna true, abrirá el el home activity, si es falsa se queda en la pantalla

        }

        btn_return.setOnClickListener {
            var intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun valida_registro(){
        val et_ca_user : EditText = findViewById(R.id.et_ca_user)
        val et_ca_email : EditText = findViewById(R.id.et_ca_email)
        val et_ca_password : EditText = findViewById(R.id.et_ca_password)
        val et_ca_confirmPass : EditText = findViewById(R.id.et_ca_confirmPass)

        val user : String = et_ca_user.text.toString()
        val email : String = et_ca_email.text.toString()
        val password: String = et_ca_password.text.toString()
        val confirmPass : String = et_ca_confirmPass.text.toString()

        if(!user.isNullOrBlank() && !email.isNullOrBlank() && !password.isNullOrBlank() && !confirmPass.isNullOrBlank()){
            if(password == confirmPass){

            //registro de firebase
                registrarFirebase(email, password)

            }else{
                Toast.makeText(this,"Las contraseñas no coinciden",Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this,"Ingresar todos los datos correctamente",Toast.LENGTH_SHORT).show()
        }
    }

    private fun registrarFirebase(email: String, password: String){

        val et_ca_user : EditText = findViewById(R.id.et_ca_user)
        val et_ca_email : EditText = findViewById(R.id.et_ca_email)
        val et_ca_password : EditText = findViewById(R.id.et_ca_password)

        val email = et_ca_email.text.toString()
        val user = et_ca_user.text.toString()
        val password = et_ca_password.text.toString()

        val us = hashMapOf("nombre" to user,
            "correo" to email,
            "contrasenia" to password)

        auth.createUserWithEmailAndPassword(email, password)
        db.collection("usuarios").document(email).set(us).addOnSuccessListener { document->

            Toast.makeText(baseContext, "Usuario creado", Toast.LENGTH_SHORT).show()

            var intent: Intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("nombre",user)

            startActivity(intent)


        }.addOnFailureListener {
            exception ->
            Toast.makeText(baseContext, "Error al crear el usuario", Toast.LENGTH_SHORT).show()

        }

        }
    }

