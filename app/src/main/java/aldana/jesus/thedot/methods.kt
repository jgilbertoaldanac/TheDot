package aldana.jesus.thedot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class methods : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_methods)

        val btn_back: ImageButton = findViewById(R.id.btn_am_return) as ImageButton

        btn_back.setOnClickListener{
            var intent: Intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}