package com.uco.pdm.toolplus.vista.fragmentSevenRegUser

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.uco.pdm.toolplus.R
import com.uco.pdm.toolplus.databinding.FragmentSevenRegUserBinding
import com.uco.pdm.toolplus.models.Usuario
import com.uco.pdm.toolplus.persistence.database.AppDatabase
import com.uco.pdm.toolplus.vista.firstActivity.FirstActivity

class Fragment_Seven_Reg_User : Fragment() {

    private var _binding: FragmentSevenRegUserBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSevenRegUserBinding.inflate(inflater, container, false)
        crear(binding.root)
        val returnLogin = binding.root.findViewById<Button>(R.id.button2)
        returnLogin.setOnClickListener {
            val intent = Intent(activity, FirstActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        db = AppDatabase.getInstance(context)
        binding.buttonCreate.setOnClickListener {
            val usuario = Usuario(
                binding.editTextTextEmailAddress2.text.toString(),
                binding.editTextTextPersonName.text.toString(),
                binding.editTextNumber.text.toString(),
                binding.editTextPhone.text.toString(),
                binding.editTextTextPostalAddress.text.toString(),
                binding.editTextTextPassword2.text.toString(),
                binding.editTextTextPersonName2.text.toString()
            )
            db.usuarioDAO().insertAll(usuario)
            Toast.makeText(context, "Usuario creado", Toast.LENGTH_LONG).show()

            val intent = Intent(activity, FirstActivity::class.java).apply {
                putExtra("EMAIL", binding.editTextTextEmailAddress2.text.toString())
                putExtra("PASS", binding.editTextTextPassword2.text.toString())
                putExtra("ROL", binding.editTextTextPersonName2.text.toString())
            }
            startActivity(intent)

        }
    }

    fun crear(view: View){
        val email = view.findViewById<EditText?>(R.id.editTextTextEmailAddress2)
        val rol = view.findViewById<EditText?>(R.id.editTextTextPersonName2)
        val pass = view.findViewById<EditText>(R.id.editTextTextPassword2)

        val createUser = view.findViewById<Button>(R.id.buttonCreate)
        createUser.setOnClickListener {
            val intent = Intent(activity, FirstActivity::class.java).apply {
                putExtra("email", email.text.toString())
                putExtra("rol", rol.text.toString())
                putExtra("pass", pass.text.toString())
            }
            startActivity(intent)
        }
    }


}