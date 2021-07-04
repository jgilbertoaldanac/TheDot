package aldana.jesus.thedot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btn_login: Button = findViewById(R.id.btn_lg_iniciarSesion) as Button
        val btn_back: ImageButton = findViewById(R.id.btn_lg_back) as ImageButton

        btn_login.setOnClickListener{
            var intent: Intent = Intent(this, Home::class.java)
            startActivity(intent)
        }

        btn_back.setOnClickListener {
            var intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}