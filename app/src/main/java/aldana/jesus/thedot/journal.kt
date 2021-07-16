package aldana.jesus.thedot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class journal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_journal)

        val btn_back: ImageButton = findViewById(R.id.btn_aj_back) as ImageButton
        val btn_comoestas: Button = findViewById(R.id.btn_comoEstasHoy) as Button
        val btn_intencionesSemana: Button = findViewById(R.id.btn_intencionesSemana) as Button
        val btn_misDias: Button = findViewById(R.id.btn_misDias) as Button

        btn_back.setOnClickListener{
            var intent: Intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        btn_comoestas.setOnClickListener{
            var intent: Intent = Intent(this, howareyoutoday::class.java)
            startActivity(intent)
        }

        btn_intencionesSemana.setOnClickListener{
            var intent: Intent = Intent(this, intentions_day::class.java)
            startActivity(intent)
        }
    }
}