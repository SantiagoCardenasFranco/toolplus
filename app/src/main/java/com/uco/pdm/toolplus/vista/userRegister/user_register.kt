package com.uco.pdm.toolplus.vista.userRegister

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.uco.pdm.toolplus.R
import com.uco.pdm.toolplus.databinding.FragmentSevenRegUserBinding
import com.uco.pdm.toolplus.models.Usuario
import com.uco.pdm.toolplus.persistence.database.AppDatabase
import com.uco.pdm.toolplus.vista.firstActivity.FirstActivity

class user_register : Fragment() {

    private lateinit var txtName:EditText
    private lateinit var idNumber:EditText
    private lateinit var cellNumber:EditText
    private lateinit var txtAddres:EditText
    private lateinit var txtEmail:EditText
    private lateinit var txtPassword:EditText
    private lateinit var txtValidate:EditText

    private var _binding: FragmentSevenRegUserBinding? = null

    private val binding get() = _binding!!
    private lateinit var db: AppDatabase

    private lateinit var database: FirebaseDatabase
    private lateinit var dbreference: DatabaseReference
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
        database= FirebaseDatabase.getInstance()
        auth= FirebaseAuth.getInstance()

        dbreference=database.reference.child("User")

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSevenRegUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        db = AppDatabase.getInstance(context)
        binding.buttonCreate.setOnClickListener {view ->
            Toast.makeText(context, "Usuario creado", Toast.LENGTH_LONG).show()
            val usuario = Usuario(
                binding.editTextTextEmailAddress2.toString(),
                binding.editTextTextPersonName.toString(),
                binding.editTextNumber.toString(),
                binding.editTextPhone.toString(),
                binding.editTextTextPostalAddress.toString(),
                binding.editTextTextPassword2.toString(),
                binding.editTextTextPersonName2.toString()
            )
            db.usuarioDAO().insertAll(usuario)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment user_register.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            user_register().apply {
                arguments = Bundle().apply {
                }
            }
    }

    private fun CharSequence?.isValidEmail() =
        !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun CrearNuevaCuenta (){

        val name: String=txtName.text.toString()
        val id: String=idNumber.text.toString()
        val cel: String=cellNumber.text.toString()
        val add: String= txtAddres.text.toString()
        val email:String=txtEmail.text.toString()
        val pasw: String=txtPassword.text.toString()
        val valid:String=txtValidate.text.toString()

        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(id) && !TextUtils.isEmpty(cel) && !TextUtils.isEmpty(add) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(pasw) && !TextUtils.isEmpty(valid)){

            auth.createUserWithEmailAndPassword(email,pasw)
                .addOnCompleteListener(this) {
                    task ->
                    if (task.isComplete){
                        val user: FirebaseUser?=auth.currentUser
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

        }







    }

    private fun action(){
        startActivity(Intent(this,FirstActivity::class.java))
    }



}