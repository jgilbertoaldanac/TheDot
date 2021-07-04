package aldana.jesus.thedot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inicio)

        val btn_login: Button = findViewById(R.id.btn_iniciaSesion) as Button
        val btn_sign_in: Button = findViewById(R.id.btn_crearCuenta) as Button

        btn_login.setOnClickListener{
            var intent: Intent = Intent(this, login::class.java)
            startActivity(intent)
        }

        btn_sign_in.setOnClickListener {
            var intent: Intent = Intent(this, create_account::class.java)
            startActivity(intent)
        }
    }
}