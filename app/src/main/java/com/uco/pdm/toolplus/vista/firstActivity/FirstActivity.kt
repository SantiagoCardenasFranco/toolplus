package com.uco.pdm.toolplus.vista.firstActivity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.uco.pdm.toolplus.R
import com.uco.pdm.toolplus.databinding.ActivityFirstBinding
import com.uco.pdm.toolplus.models.Usuario
import com.uco.pdm.toolplus.persistence.database.AppDatabase
import com.uco.pdm.toolplus.vista.fragmentSevenRegUser.Fragment_Seven_Reg_User
import com.uco.pdm.toolplus.vista.recyclerViewUser.ReciclerViewToolUserActivity
import com.uco.pdm.toolplus.vista.recyclerViewUser.RecyclerViewToolsActivity

class FirstActivity : AppCompatActivity() {
    private var _binding: ActivityFirstBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: AppDatabase
    private lateinit var user: EditText
    private lateinit var pass: EditText
    private lateinit var auth: FirebaseAuth


    var email = ""
    var rol = ""
    var passw = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(this)
        user = findViewById(R.id.edusername)
        pass = findViewById(R.id.edcontra)
        auth = FirebaseAuth.getInstance()

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
        val user:String= user.text.toString()
        val pass:String= pass.text.toString()
        val userDB: Usuario = db.usuarioDAO().getUser(user, pass)
        if(!TextUtils.isEmpty(user) && !TextUtils.isEmpty(pass) ){
            auth.signInWithEmailAndPassword(user, pass)
                .addOnCompleteListener(this){
                    task ->
                    if(task.isSuccessful){
                        println("User "+task.result)


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
                    }
                    else{
                        Toast.makeText(this, "Error en la auth", Toast.LENGTH_LONG).show()
                        if (userDB == null){
                            Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_LONG).show()
                        }
                    }
                }
        }





    }
}