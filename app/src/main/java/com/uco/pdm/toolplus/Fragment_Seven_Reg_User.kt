package com.uco.pdm.toolplus

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment_Seven_Reg_User.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment_Seven_Reg_User : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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