package aldana.jesus.thedot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btn_config: ImageButton = findViewById(R.id.btn_ah_config) as ImageButton
        val btn_profile: Button = findViewById(R.id.btn_ah_perfil) as Button
        val btn_methods: Button = findViewById(R.id.btn_ah_metodos) as Button
        val btn_journal: Button = findViewById(R.id.btn_ah_journal) as Button
        val btn_options: Button = findViewById(R.id.btn_ah_opciones) as Button

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