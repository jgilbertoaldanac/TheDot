package aldana.jesus.thedot

import android.content.Context
import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class afirmations : AppCompatActivity() {

    private lateinit var storage: FirebaseFirestore
    private lateinit var usuario: FirebaseAuth
    var listaAfirmaciones = ArrayList<Afirmacion>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_afirmations)

        storage = FirebaseFirestore.getInstance()
        usuario = FirebaseAuth.getInstance()

        val btn_back: ImageButton = findViewById(R.id.btn_aa_return) as ImageButton
        val btn_add: Button= findViewById(R.id.btn_addAfirmacion) as Button
        var et_afirmacion: EditText = findViewById(R.id.et_addAfirmation) as EditText

        var listview: ListView = findViewById(R.id.listAfirmaciones) as ListView
        var adaptador = AdaptorAfirmaciones(this, listaAfirmaciones)
        val btn_guarda: Button = findViewById(R.id.btn_saveAfir) as Button

        listview.adapter= adaptador

        et_afirmacion.visibility = View.INVISIBLE
        btn_guarda.visibility = View.INVISIBLE

        cargarAfirmaciones(usuario.currentUser?.email.toString())

        btn_back.setOnClickListener{
            var intent: Intent = Intent(this, profile::class.java)
            startActivity(intent)
        }

        btn_add.setOnClickListener {
            et_afirmacion.visibility =View.VISIBLE
            btn_guarda.visibility = View.VISIBLE
        }

        btn_guarda.setOnClickListener {
            guardarAfirmacion(usuario.currentUser?.email.toString())
        }
    }

    fun cargarAfirmaciones(email: String){
        storage.collection("afirmaciones").document(email).get().addOnSuccessListener { doc ->
                var afirmacion: String = doc.getString("afirmacion").toString()
                var fecha: String = doc.getString("fecha").toString()
                listaAfirmaciones.add(Afirmacion(email, afirmacion, fecha))

        }.addOnFailureListener { exception ->
            Toast.makeText(baseContext, "Error al cargar las afirmaciones", Toast.LENGTH_SHORT).show()
        }


    }

    fun guardarAfirmacion(email: String){
        val et_addAfirmacion: EditText = findViewById(R.id.et_addAfirmation) as EditText

        val add_afirmacion: String = et_addAfirmacion.text.toString()
        val sdf = SimpleDateFormat("dd/M/yyyy")
        val fecha = sdf.format(Date())

        if(!add_afirmacion.isNullOrBlank()){
            val afirmacion = hashMapOf("correo" to email,
                "afirmacion" to add_afirmacion,
                "fecha" to fecha)

            storage.collection("afirmaciones").document(email).set(afirmacion).addOnSuccessListener {

                Toast.makeText(baseContext, "Afirmación guardada", Toast.LENGTH_SHORT).show()

            }.addOnFailureListener { exception ->
                Toast.makeText(baseContext, "Error al guardar afirmación ", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(baseContext, "Afirmación vacía, ingresela por favor. ", Toast.LENGTH_SHORT).show()
        }
    }



    class AdaptorAfirmaciones: BaseAdapter {

        var contexto: Context?= null
        var afirmaciones = ArrayList<Afirmacion>()


        constructor(contexto: Context, productos: ArrayList<Afirmacion>){
            this.contexto =contexto
            this.afirmaciones = afirmaciones
        }
        override fun getCount(): Int {
            return  afirmaciones.size
        }

        override fun getItem(p0: Int): Any {
            return afirmaciones[p0]
        }

        override fun getItemId(p0: Int): Long {
            return 1
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var afirm = afirmaciones[p0]
            var inflator = LayoutInflater.from(contexto)
            var vista = inflator.inflate(R.layout.afirmacion, null)

            var et_afirmacion = vista.findViewById(R.id.tx_afirmacion) as TextView


            return vista

        }

    }

}