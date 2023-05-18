package com.uco.pdm.toolplus.vista.firstActivity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.firebase.database.FirebaseDatabase
import com.uco.pdm.toolplus.R
import com.uco.pdm.toolplus.databinding.ActivityFirstBinding
import com.uco.pdm.toolplus.databinding.FragmentSevenRegUserBinding
import com.uco.pdm.toolplus.models.Usuario
import com.uco.pdm.toolplus.persistence.database.AppDatabase
import com.uco.pdm.toolplus.vista.fragmentSevenRegUser.Fragment_Seven_Reg_User
import com.uco.pdm.toolplus.vista.recyclerViewUser.ReciclerViewToolUserActivity
import com.uco.pdm.toolplus.vista.recyclerViewUser.RecyclerViewToolsActivity

class FirstActivity : AppCompatActivity() {
    private var _binding: ActivityFirstBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: AppDatabase
    private lateinit var database: FirebaseDatabase 

    var email = ""
    var rol = ""
    var passw = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(this)


        /*binding.btnentrar.setOnClickListener{
            val user: EditText = findViewById(R.id.edusername)
            val pass: EditText = findViewById(R.id.edcontra)

            val userDB = db.usuarioDAO().getUser(user.text.toString(), pass.text.toString())
            print("aqui " + userDB)
            if(userDB != null && userDB.decision == "admin"){
                Toast.makeText(this, "Usuario encontrado", Toast.LENGTH_LONG).show()
                val intent = Intent(this, RecyclerViewToolsActivity::class.java).apply {
                    putExtra("EMAIL", userDB.correo)
                }
                startActivity(intent)
            }
        }*/
    }

    fun RegistroData(view: View){
        val fmanager = supportFragmentManager
        val fmanagertrs = fmanager.beginTransaction()
        val fragment = Fragment_Seven_Reg_User()

        fmanagertrs.add(R.id.loginUser, fragment).commit()
    }

    fun entrar(view: View){
        db = AppDatabase.getInstance(this)
        val user: EditText = findViewById(R.id.edusername)
        val pass: EditText = findViewById(R.id.edcontra)

        val userDB: Usuario = db.usuarioDAO().getUser(user.text.toString(), pass.text.toString())

        if(userDB != null && userDB.decision == "admin"){
            Toast.makeText(this, "Usuario encontrado", Toast.LENGTH_LONG).show()
            val intent = Intent(this, RecyclerViewToolsActivity::class.java).apply {
                putExtra("EMAIL", userDB.correo)
            }
            startActivity(intent)
        }
        else if(userDB != null && userDB.decision == "cliente"){
            Toast.makeText(this, "Usuario encontrado", Toast.LENGTH_LONG).show()
            val intent = Intent(this, ReciclerViewToolUserActivity::class.java).apply {
                putExtra("EMAIL", userDB.correo)
            }
            startActivity(intent)
        }
        else if (userDB == null){
            Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_LONG).show()
        }

    }
}