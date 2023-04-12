package com.uco.pdm.toolplus.vista.registerToolFragment

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.uco.pdm.toolplus.R
import com.uco.pdm.toolplus.vista.recyclerViewUser.RecyclerViewToolsActivity

class RegisterToolFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView : View = inflater.inflate(R.layout.fragment_register_tool, container, false)

        // Inflate the layout for this fragment
        val retornoTools = rootView.findViewById<FloatingActionButton>(R.id.cancelRegister)
        retornoTools.setOnClickListener {
            seeDialog()
        }
        return rootView
    }

    private fun seeDialog(){
        val builder = AlertDialog.Builder(activity)
        val inflater = layoutInflater
        val view = inflater.inflate(R.layout.dialog_personalized, null)
        builder.setView(view)

        val dialog = builder.create()
        dialog.show()

        val btnYes = view.findViewById<LottieAnimationView>(R.id.btnYes)
        btnYes.setOnClickListener {
            Toast.makeText(activity, "Se canceló la operación...", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
            val intent = Intent(activity, RecyclerViewToolsActivity::class.java)
            startActivity(intent)
        }

        val btnNo = view.findViewById<LottieAnimationView>(R.id.btnNo)
        btnNo.setOnClickListener {
            Toast.makeText(activity, "Se reaunuda la operación...", Toast.LENGTH_SHORT).show()
            dialog.dismiss()

        }
    }
}