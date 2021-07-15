package aldana.jesus.thedot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.ViewFlipper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class HomeActivity : AppCompatActivity() {

    //variables donde se guardaran el acceso a la base de datos y el Authentication del user
    private lateinit var storage: FirebaseFirestore
    private lateinit var usuario: FirebaseAuth

    //private val db= FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btn_config: ImageButton = findViewById(R.id.btn_ah_config) as ImageButton
        val btn_profile: Button = findViewById(R.id.btn_ah_perfil) as Button
        val btn_methods: Button = findViewById(R.id.btn_ah_metodos) as Button
        val btn_journal: Button = findViewById(R.id.btn_ah_journal) as Button
        val btn_options: Button = findViewById(R.id.btn_ah_opciones) as Button
        val tv_bienvenida: TextView =findViewById(R.id.tv_ah_greeting) as TextView

        //val value : Intent = intent
        //var nombre= value.getStringExtra("nombre")
        //Val imagenes = arrayOf(R.drawable.sabias, R.drawable.ve_por_helado, R.drawable.playlist, R.drawable.foto_del_dia)

        //Al usar lateinit en las variables anteriores, aqui los estamos instanciando
        //tanto la base de datos como el usuario
        storage = FirebaseFirestore.getInstance()
        usuario = FirebaseAuth.getInstance()

        var nombre: String = ""

        //storage busca en una coleccion
        storage.collection("usuarios")
                //este especifica que campo comparar de la coleccion, buscamos el usuario cuyo valor en "correo" coincida con el email del authentication
            .whereEqualTo("correo",usuario.currentUser?.email)
            .get()
                //si logra obtene con exito en la linea anterior, procede a utilizar la informacion obtenida
            .addOnSuccessListener {
                //el "it" son los elementos dentro de la coleccion usuarios, (por aun no saber el como hacer la busqueda unitaria dejo el forEach)
                it.forEach {

                    //se carga en la variable el valor dentro de "nombre" (como esta el forEach, en el hipotetico caso de que hubieran mas documentos de "usuarios"
                    //con el mismo correo de coincidencia, estaria escribiendolos uno por uno y reemplazando el anterior, usen este conocimiento a conciencia XD)
                    nombre = it.getString("nombre").toString()
                    tv_bienvenida.setText("¡Hola, $nombre!")
                }

        }



        btn_config.setOnClickListener{
            var intent: Intent = Intent(this, configuration::class.java)
            startActivity(intent)
        }

        btn_profile.setOnClickListener{
            var intent: Intent = Intent(this, profile::class.java)
            startActivity(intent)
        }

        btn_methods.setOnClickListener{
            var intent: Intent = Intent(this, methods::class.java)
            startActivity(intent)
        }

        btn_journal.setOnClickListener{
            var intent: Intent = Intent(this, journal::class.java)
            startActivity(intent)
        }

        btn_options.setOnClickListener{
            var intent: Intent = Intent(this, information::class.java)
            startActivity(intent)
        }

    }


   }


