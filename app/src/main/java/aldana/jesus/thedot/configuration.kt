package aldana.jesus.thedot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class configuration : AppCompatActivity() {
    //private val db= FirebaseFirestore.getInstance()
    private lateinit var storage: FirebaseFirestore
    private lateinit var usuario: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuration)

        val btn_close: Button = findViewById(R.id.btn_ac_cerrarSesion) as Button
        val btn_back: ImageButton = findViewById(R.id.img_ac_back) as ImageButton
        var et_nombre: EditText = findViewById(R.id.et_nombre) as EditText
        var et_fechaNac: EditText = findViewById(R.id.et_FechaNacimiento) as EditText
        var et_ubicacion: EditText = findViewById(R.id.et_ubicacion) as EditText
        var et_ocupacion: EditText = findViewById(R.id.et_ocupacion) as EditText
        var et_contrasenia: EditText = findViewById(R.id.et_contrasenia) as EditText

        storage = FirebaseFirestore.getInstance()
        usuario = FirebaseAuth.getInstance()

        var nombre: String = ""
        var fechaNac: String = ""
        var ubicacion: String = ""
        var ocupacion: String = ""
        var contrasenia: String = ""

        storage.collection("usuarios").whereEqualTo("correo",usuario.currentUser?.email).get().addOnSuccessListener {
            it.forEach {
                nombre = it.getString("nombre").toString()
                fechaNac = it.getString("fechaNac").toString()
                ubicacion = it.getString("ubicacion").toString()
                ocupacion = it.getString("ocupacion").toString()
                contrasenia = it.getString("contrasenia").toString()
                }
            }

        if(!nombre.isNullOrBlank()){
            et_nombre.setText(nombre)
        }
        if(!fechaNac.isNullOrBlank()){
            et_fechaNac.setText(fechaNac)
        }
        if(!ubicacion.isNullOrBlank()){
            et_ubicacion.setText(ubicacion)
        }
        if(!ocupacion.isNullOrBlank()){
            et_ocupacion.setText(ocupacion)
        }
        if(!contrasenia.isNullOrBlank()){
            et_contrasenia.setText(contrasenia)
        }

        val data = hashMapOf(
            "nombre" to nombre,
            "fechaNac" to fechaNac,
            "ubicacion" to ubicacion,
            "ocupacion" to ocupacion,
            "contrasenia" to contrasenia
        )

        btn_close.setOnClickListener{
            storage.collection("usuarios").document(usuario.currentUser?.email.toString()).set(data)
            var intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btn_back.setOnClickListener {
            var intent: Intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }

}


