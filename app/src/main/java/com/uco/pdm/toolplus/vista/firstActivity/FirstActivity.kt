package com.uco.pdm.toolplus.vista.firstActivity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.uco.pdm.toolplus.R
import com.uco.pdm.toolplus.vista.fragmentSevenRegUser.Fragment_Seven_Reg_User
import com.uco.pdm.toolplus.vista.recyclerViewUser.ReciclerViewToolUserActivity
import com.uco.pdm.toolplus.vista.recyclerViewUser.RecyclerViewToolsActivity

class FirstActivity : AppCompatActivity() {
    var email = ""
    var rol = ""
    var passw = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        email = intent.getStringExtra("email").toString()
        rol = intent.getStringExtra("rol").toString()
        passw = intent.getStringExtra("pass").toString()

        Toast.makeText(this, "Email del usuario: " + rol + " " + email + " "+ passw, Toast.LENGTH_LONG).show()

        val user: EditText = findViewById(R.id.edusername)
        val pass: EditText = findViewById(R.id.edcontra)
        user.setText(email)
        pass.setText(passw)
    }

    fun RegistroData(view: View){
        val fmanager = supportFragmentManager
        val fmanagertrs = fmanager.beginTransaction()
        val fragment = Fragment_Seven_Reg_User()

        fmanagertrs.add(R.id.loginUser, fragment).commit()
    }

    fun entrar(view: View){
        val user: EditText = findViewById(R.id.edusername)
        val pass: EditText = findViewById(R.id.edcontra)
        user.setText(email)
        pass.setText(passw)
        if((user.text.toString() == "admin" && pass.text.toString() == "admin") || rol == "admin"){
            val intent = Intent(this, RecyclerViewToolsActivity::class.java).apply {
                putExtra("EMAIL", email)
            }
            startActivity(intent)

        }else if((user.text.toString() == "client" && pass.text.toString() == "123")|| rol == "user"){
            val intent = Intent(this, ReciclerViewToolUserActivity::class.java).apply {
                putExtra("EMAIL", "client")
            }
            startActivity(intent)
        }else{
            Toast.makeText(this, "Usuario o contaseña no valida", Toast.LENGTH_LONG).show()
        }
    }
}