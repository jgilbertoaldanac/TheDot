package aldana.jesus.thedot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class configuration : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuration)

        val btn_close: Button = findViewById(R.id.btn_ac_cerrarSesion) as Button
        val btn_back: ImageButton = findViewById(R.id.img_ac_back) as ImageButton

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