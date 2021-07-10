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
import com.google.firebase.ktx.Firebase

class configuration : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

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

        btn_close.setOnClickListener{
            var intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btn_back.setOnClickListener {
            var intent: Intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }

}


