package com.uco.pdm.toolplus

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class FirstActivity : AppCompatActivity() {
    var email = ""
    var rol = ""
    var passw = ""
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        email = intent.getStringExtra("email").toString()
        rol = intent.getStringExtra("rol").toString()
        passw = intent.getStringExtra("pass").toString()

        //Toast.makeText(this, "Email del usuario: " + dataUser, Toast.LENGTH_LONG).show()

        val createUser: Button = findViewById(R.id.registerData)
        createUser.setOnClickListener {
            val fmanager = supportFragmentManager
            val fmanagertrs = fmanager.beginTransaction()
            val fragment = Fragment_Seven_Reg_User()

            fmanagertrs.add(R.id.loginUser, fragment).commit()
        }
    }

    fun entrar(view: View){
        if(rol == "admin"){
            val intent = Intent(this, RecyclerViewToolsActivity::class.java).apply {
                putExtra("EMAIL", email)
            }
            startActivity(intent)

        }else if(rol == "user"){
            val intent = Intent(this, ReciclerViewToolUserActivity::class.java).apply {
                putExtra("EMAIL", email)
            }
            startActivity(intent)
        }else{
            Toast.makeText(this, "Usuario o contaseña no valida", Toast.LENGTH_LONG).show()
        }
    }
}