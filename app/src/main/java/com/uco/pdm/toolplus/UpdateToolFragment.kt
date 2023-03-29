package com.uco.pdm.toolplus

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.imageview.ShapeableImageView

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
/**
 * A simple [Fragment] subclass.
 * Use the [UpdateToolFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class UpdateToolFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView : View = inflater.inflate(R.layout.fragment_update_tool, container, false)

        val dataBundle = arguments
        val image = dataBundle!!.getInt("imageView")
        val name = dataBundle!!.getString("nameToolUpdate")
        val description = dataBundle.getString("descriptionToolUpdate")
        val price = dataBundle.getInt("priceToolUpdate")
        val count = dataBundle.getInt("countToolUpdate")
        val user = dataBundle.getString("email")
        //Toast.makeText(activity, "Email del usuario: " + user, Toast.LENGTH_LONG).show()


        val imageTool = rootView.findViewById<ShapeableImageView>(R.id.imageViewTool)
        val nameTool = rootView.findViewById<EditText>(R.id.nameToolUpdate)
        val descriptionTool = rootView.findViewById<EditText>(R.id.descriptionToolUpdate)
        val princeTool = rootView.findViewById<EditText>(R.id.priceToolUpdate)
        val countTool = rootView.findViewById<EditText>(R.id.countToolUpdate)
        imageTool.setImageResource(image)
        nameTool.setText(name)
        descriptionTool.setText(description)
        princeTool.setText(price.toString())
        countTool.setText(count.toString())

        val retornoTools = rootView.findViewById<FloatingActionButton>(R.id.cancelUpdate)
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