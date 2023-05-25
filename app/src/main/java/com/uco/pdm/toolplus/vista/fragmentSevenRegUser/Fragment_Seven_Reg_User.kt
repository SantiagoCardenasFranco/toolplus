package com.uco.pdm.toolplus.vista.fragmentSevenRegUser

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.uco.pdm.toolplus.R
import com.uco.pdm.toolplus.databinding.FragmentSevenRegUserBinding
import com.uco.pdm.toolplus.models.Usuario
import com.uco.pdm.toolplus.persistence.database.AppDatabase
import com.uco.pdm.toolplus.vista.firstActivity.FirstActivity

class Fragment_Seven_Reg_User : Fragment() {

    private var _binding: FragmentSevenRegUserBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: AppDatabase

    private lateinit var txtName:EditText
    private lateinit var idNumber:EditText
    private lateinit var cellNumber:EditText
    private lateinit var txtAddres:EditText
    private lateinit var txtEmail:EditText
    private lateinit var txtPassword:EditText
    private lateinit var txtValidate:EditText
    private lateinit var database: FirebaseDatabase
    private lateinit var dbreference: DatabaseReference
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSevenRegUserBinding.inflate(inflater, container, false)

        txtName = binding.root.findViewById(R.id.editTextTextPersonName)
        idNumber = binding.root.findViewById(R.id.editTextNumber)
        cellNumber = binding.root.findViewById(R.id.editTextPhone)
        txtAddres = binding.root.findViewById(R.id.editTextTextPostalAddress)
        txtEmail = binding.root.findViewById(R.id.editTextTextEmailAddress2)
        txtPassword = binding.root.findViewById(R.id.editTextTextPassword2)
        txtValidate = binding.root.findViewById(R.id.editTextTextPersonName2)
        crear(binding.root)
        val returnLogin = binding.root.findViewById<Button>(R.id.button2)
        returnLogin.setOnClickListener {
            val intent = Intent(activity, FirstActivity::class.java)
            startActivity(intent)
        }

        database= FirebaseDatabase.getInstance()
        auth= FirebaseAuth.getInstance()

        dbreference=database.reference.child("User")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        db = AppDatabase.getInstance(context)
        binding.buttonCreate.setOnClickListener {
            crearNuevaCuenta()
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

            val intent = Intent(activity, FirstActivity::class.java)
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

    private fun crearNuevaCuenta (){

        val name: String=txtName.text.toString()
        val id: String=idNumber.text.toString()
        val cel: String=cellNumber.text.toString()
        val add: String= txtAddres.text.toString()
        val email:String=txtEmail.text.toString()
        val pasw: String=txtPassword.text.toString()
        val valid:String=txtValidate.text.toString()

        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(id) && !TextUtils.isEmpty(cel) && !TextUtils.isEmpty(add) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(pasw) && !TextUtils.isEmpty(valid)){

            auth.createUserWithEmailAndPassword(email,pasw)
                .addOnCompleteListener{
                        task ->
                    if (task.isComplete){
                        println("Task Complete " + task.result)
                        val user: FirebaseUser?=auth.currentUser
                        println("User es " + user)
                        verifyEmail(user)
                        val userBD=dbreference.child(user?.uid.toString())
                        userBD.child("name").setValue(name)
                        userBD.child("id").setValue(id)
                        userBD.child("cel").setValue(cel)
                        userBD.child("add").setValue(add)
                        userBD.child("email").setValue(email)
                        userBD.child("pasw").setValue(pasw)
                        userBD.child("valid").setValue(valid)
                        action()

                    }

                }
            Toast.makeText(context, "Usuario creado firebase", Toast.LENGTH_LONG).show()

        }
    }

    private fun action(){
        startActivity(Intent(requireContext().applicationContext,FirstActivity::class.java))
    }

    private fun verifyEmail(user: FirebaseUser?){
        user?.sendEmailVerification()
            ?.addOnCompleteListener{
                    task ->
                if(task.isComplete){
                    Toast.makeText(requireContext().applicationContext, "Correo enviado", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(requireContext().applicationContext, "Error al enviar correo", Toast.LENGTH_LONG).show()
                }
            }
    }


}