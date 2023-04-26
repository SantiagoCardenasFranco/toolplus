package com.uco.pdm.toolplus.vista.userRegister

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.uco.pdm.toolplus.R
import com.uco.pdm.toolplus.databinding.FragmentSevenRegUserBinding
import com.uco.pdm.toolplus.models.Usuario
import com.uco.pdm.toolplus.persistence.database.AppDatabase

class user_register : Fragment() {

    private var _binding: FragmentSevenRegUserBinding? = null

    private val binding get() = _binding!!
    private lateinit var db: AppDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
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
}