package com.uco.pdm.toolplus

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.airbnb.lottie.LottieAnimationView

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class RegisterToolFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    lateinit var btnRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_register_tool, container, false)

        btnRegister = view.findViewById(R.id.addRegister)
        btnRegister.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                dialogPersonalized()
            }

        })

        return view
    }

    private fun dialogPersonalized(){
        val builder:AlertDialog.Builder = AlertDialog.Builder(activity)
        val inflater:LayoutInflater = layoutInflater
        val view:View = inflater.inflate(R.layout.dialog_personalized, null)

        builder.setView(view)

        val dialog:AlertDialog = builder.create()
        dialog.show()

        val btnYes:LottieAnimationView = view.findViewById(R.id.btnYes)
        btnYes.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                //Realiza tarea
                dialog.dismiss()
            }
        })

        val btnNo:LottieAnimationView = view.findViewById(R.id.btnNo)
        btnNo.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p1: View?) {
                //Realiza tarea
                dialog.dismiss()
            }
        })
    }

    /*private fun animation(imageView: LottieAnimationView, animation: Int, action:Boolean){
        if (action){
            imageView.setAnimation(animation)
            imageView.playAnimation()
        }else{
            imageView.setAnimation(animation)
            imageView.playAnimation()
        }
    }*/


}