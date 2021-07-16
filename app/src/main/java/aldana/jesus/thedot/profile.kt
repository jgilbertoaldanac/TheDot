package aldana.jesus.thedot

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.android.gms.common.data.DataHolder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import org.w3c.dom.Text
import java.io.ByteArrayOutputStream
import java.io.File

class profile : AppCompatActivity() {

    private lateinit var storage: FirebaseFirestore
    private lateinit var fotos: FirebaseStorage
    private lateinit var usuario: FirebaseAuth
    private lateinit var ivAvatar: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val btn_back: ImageButton = findViewById(R.id.btn_ap_return) as ImageButton
        val btn_reminders: Button = findViewById(R.id.btn_ap_reminders) as Button
        val btn_afirmations: Button = findViewById(R.id.btn_ap_afirmations) as Button
        val btn_diary: Button = findViewById(R.id.btn_ap_diary) as Button
        val btn_goals: Button = findViewById(R.id.btn_ap_goals) as Button
        val btn_photo: ImageButton = findViewById(R.id.ib_ap_simbolo) as ImageButton
        val tv_nombre: TextView = findViewById(R.id.tv_ap_name) as TextView
        var et_text: EditText = findViewById(R.id.et_ap_introduction) as EditText

        storage = FirebaseFirestore.getInstance()
        usuario = FirebaseAuth.getInstance()
        //fotos = Firebase.storage

        storage.collection("usuarios").whereEqualTo("correo",usuario.currentUser?.email).get().addOnSuccessListener {
            it.forEach {
                var nombre = it.getString("nombre").toString()
                tv_nombre.setText(nombre)
            }
        }

        storage.collection("presentacion").whereEqualTo("email",usuario.currentUser?.email).get().addOnSuccessListener {
            it.forEach {
                if(!it.getString("greetings").toString().equals("")){
                    et_text.setText(it.getString("greetings").toString())
                }

            }
        }

        var email: String = usuario.currentUser?.email.toString()

        btn_back.setOnClickListener{
            if(!et_text.equals("")){
                storage.collection("presentacion").document(email).set(
                    hashMapOf("email" to email, "greetings" to et_text.text.toString()))

            }

            var intent: Intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        btn_photo.setOnClickListener {
            subirImagen()
        }

        btn_reminders.setOnClickListener {
            var intent: Intent = Intent(this, profile::class.java)
            startActivity(intent)
        }

        btn_afirmations.setOnClickListener {
            var intent: Intent = Intent(this, afirmations::class.java)
            startActivity(intent)
        }

        btn_diary.setOnClickListener {
            var intent: Intent = Intent(this, diary::class.java)
            startActivity(intent)
        }

        btn_goals.setOnClickListener {
            var intent: Intent = Intent(this, profile::class.java)
            startActivity(intent)
        }
    }

    private fun subirImagen(){
        val storageRef = fotos.reference
        val rutaImagen = storageRef.child("perfiles/"+usuario.currentUser?.email+"/images/avatar.jpeg")

        val bitmap = (ivAvatar.drawable as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        var uploadTask = rutaImagen.putBytes(data)
        uploadTask.addOnFailureListener{
        //
        }.addOnSuccessListener { taskSnapshot ->
        //
        }
    }


}