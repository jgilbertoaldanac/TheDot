package aldana.jesus.thedot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inicio)

        val buttonIniciarSesion: Button = findViewById(R.id.btn_iniciaSesion) as Button

        buttonIniciarSesion.setOnClickListener{
            var intent: Intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }
}