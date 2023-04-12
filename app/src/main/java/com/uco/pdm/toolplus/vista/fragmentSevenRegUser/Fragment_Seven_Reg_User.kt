package com.uco.pdm.toolplus.vista.fragmentSevenRegUser

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.uco.pdm.toolplus.R
import com.uco.pdm.toolplus.vista.firstActivity.FirstActivity

class Fragment_Seven_Reg_User : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment__seven__reg__user, container, false)
        crear(rootView)
        val returnLogin = rootView.findViewById<Button>(R.id.button2)
        returnLogin.setOnClickListener {
            val intent = Intent(activity, FirstActivity::class.java)
            startActivity(intent)
        }


        return rootView
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