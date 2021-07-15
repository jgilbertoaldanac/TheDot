package aldana.jesus.thedot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.ViewFlipper
import com.google.firebase.firestore.FirebaseFirestore

class HomeActivity : AppCompatActivity() {
    private val db= FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btn_config: ImageButton = findViewById(R.id.btn_ah_config) as ImageButton
        val btn_profile: Button = findViewById(R.id.btn_ah_perfil) as Button
        val btn_methods: Button = findViewById(R.id.btn_ah_metodos) as Button
        val btn_journal: Button = findViewById(R.id.btn_ah_journal) as Button
        val btn_options: Button = findViewById(R.id.btn_ah_opciones) as Button
        val tv_bienvenida: TextView =findViewById(R.id.tv_ah_greeting) as TextView

        val value : Intent = intent
        var nombre= value.getStringExtra("nombre")


      //  val imagenes = arrayOf(R.drawable.sabias, R.drawable.ve_por_helado, R.drawable.playlist, R.drawable.foto_del_dia)


        btn_config.setOnClickListener{
            var intent: Intent = Intent(this, configuration::class.java)
            startActivity(intent)
        }

        tv_bienvenida.setText("¡Hola, $nombre!")

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


