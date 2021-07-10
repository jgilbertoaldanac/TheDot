package aldana.jesus.thedot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val btn_back: ImageButton = findViewById(R.id.btn_ap_return) as ImageButton
        val btn_reminders: Button = findViewById(R.id.btn_ap_reminders) as Button
        val btn_afirmations: Button = findViewById(R.id.btn_ap_afirmations) as Button
        val btn_diary: Button = findViewById(R.id.btn_ap_diary) as Button
        val btn_goals: Button = findViewById(R.id.btn_ap_goals) as Button


        btn_back.setOnClickListener{
            var intent: Intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        btn_reminders.setOnClickListener {
            var intent: Intent = Intent(this, profile::class.java)
            startActivity(intent)
        }

        btn_afirmations.setOnClickListener {
            var intent: Intent = Intent(this, afirmations::class.java)
            startActivity(intent)
        }

        btn_diary.setOnClickListener {
            var intent: Intent = Intent(this, diary::class.java)
            startActivity(intent)
        }

        btn_goals.setOnClickListener {
            var intent: Intent = Intent(this, profile::class.java)
            startActivity(intent)
        }
    }
}