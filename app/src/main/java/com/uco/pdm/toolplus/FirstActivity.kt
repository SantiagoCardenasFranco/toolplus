package com.uco.pdm.toolplus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class FirstActivity : AppCompatActivity() {
    var USER = "USER"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        val btnIncioSesion: Button = findViewById(R.id.btnentrar)
    }

    fun entrar(view: View){
        val user: EditText = findViewById(R.id.edusername)
        val pass: EditText = findViewById(R.id.edcontra)
        if(user.text.toString() == "admin" && pass.text.toString() == "admin"){
            val intent = Intent(this, RecyclerViewToolsActivity::class.java).apply {
                putExtra(USER, "admin")
            }
            startActivity(intent)

        }else if(user.text.toString() == "client" && pass.text.toString() == "123"){
            val intent = Intent(this, ReciclerViewToolUserActivity::class.java).apply {
                putExtra(USER, "client")
            }
            startActivity(intent)
        }else{
            Toast.makeText(this, "Usuario o contaseña no valida", Toast.LENGTH_LONG).show()
        }
    }
}