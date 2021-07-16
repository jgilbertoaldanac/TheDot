package aldana.jesus.thedot

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.w3c.dom.Text
import java.time.Instant

class howareyoutoday : AppCompatActivity() {

    private lateinit var storage: FirebaseFirestore
    private lateinit var usuario: FirebaseAuth

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_howareyoutoday)

        val btn_back: ImageButton = findViewById(R.id.back_ceh) as ImageButton
        val et_comoestas: EditText = findViewById(R.id.et_ceh) as EditText

        storage = FirebaseFirestore.getInstance()
        usuario = FirebaseAuth.getInstance()

        var email: String = usuario.currentUser?.email.toString()
        val fecha = Instant.now()

        val tv_fh_ceh: TextView = findViewById(R.id.tv_fh_ceh) as TextView

        tv_fh_ceh.setText(fecha.toString())

        btn_back.setOnClickListener{
            if(!et_comoestas.equals("")){
                storage.collection("journal").document(email).set(
                    hashMapOf("email" to email, "como_estas_hoy" to et_comoestas.text.toString()))
            }
            var intent: Intent = Intent(this, journal::class.java)
            startActivity(intent)
        }


    }
}