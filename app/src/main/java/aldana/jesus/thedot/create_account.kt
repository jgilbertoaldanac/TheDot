package aldana.jesus.thedot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class create_account : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        val btn_create_account: Button = findViewById(R.id.btn_ca_createAccount) as Button
        val btn_return: ImageButton = findViewById(R.id.btn_ca_return) as ImageButton

        btn_create_account.setOnClickListener{
            var intent: Intent = Intent(this, Home::class.java)
            startActivity(intent)
        }

        btn_return.setOnClickListener {
            var intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}