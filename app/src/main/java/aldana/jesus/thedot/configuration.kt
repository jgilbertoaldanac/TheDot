package aldana.jesus.thedot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
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

        var tv_config : TextView = findViewById(R.id.tv_Configuracion) as TextView

        storage = FirebaseFirestore.getInstance()
        usuario = FirebaseAuth.getInstance()

        var nombre: String = ""
        var fechaNac: String = ""
        var ubicacion: String = ""
        var ocupacion: String = ""
        var contrasenia: String = ""



        storage.collection("usuarios").whereEqualTo("correo",usuario.currentUser?.email).get().addOnSuccessListener {
            it.forEach {

                if(nombre.isNullOrBlank()){
                    tv_config.setText(it.getString("nombre").toString())
                     var nombrebueno = nombre
                }else{
                    nombre = "no entro al if"
                }
                if(fechaNac.isNullOrBlank()){
                    et_fechaNac.setText(it.getString("fechaNac").toString())
                }else{
                    fechaNac ="no entro"
                }
                if(ubicacion.isNullOrBlank()){
                    et_ubicacion.setText(it.getString("ubicacion").toString())
                }
                if(ocupacion.isNullOrBlank()){
                    et_ocupacion.setText(it.getString("ocupacion").toString())
                }
                if(contrasenia.isNullOrBlank()){
                    et_contrasenia.setText(it.getString("contrasenia").toString())
                }


                }
            }

        nombre= et_nombre.text.toString()
        contrasenia = et_contrasenia.text.toString()
        fechaNac = et_fechaNac.text.toString()
        ubicacion = et_ubicacion.text.toString()
        ocupacion= et_ocupacion.text.toString()

        println(nombre)

        val data = hashMapOf(
            "nombre" to nombre,
            "fechaNac" to fechaNac,
            "ubicacion" to ubicacion,
            "ocupacion" to ocupacion,
            "contrasenia" to contrasenia)

        btn_close.setOnClickListener{

            var intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btn_back.setOnClickListener {
            storage.collection("usuarios").document(usuario.currentUser?.email.toString()).set(data).addOnSuccessListener {
                Toast.makeText(baseContext, "Afirmación guardada", Toast.LENGTH_SHORT).show()

            }.addOnFailureListener { exception ->
                Toast.makeText(baseContext, "valió pedo no jaló", Toast.LENGTH_SHORT).show()

            }

            var intent: Intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }

}


