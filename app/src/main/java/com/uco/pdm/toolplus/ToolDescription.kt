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
import android.widget.TextView
import android.widget.Toast
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.imageview.ShapeableImageView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ToolDescription.newInstance] factory method to
 * create an instance of this fragment.
 */
class ToolDescription : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView : View = inflater.inflate(R.layout.fragment_tool_description, container, false)

        val dataBundle = arguments
        val image = dataBundle!!.getInt("imageView")
        val name = dataBundle!!.getString("nameToolUpdate")
        val description = dataBundle.getString("descriptionToolUpdate")
        val price = dataBundle.getInt("priceToolUpdate")
        val count = dataBundle.getInt("countToolUpdate")

        val imageTool = rootView.findViewById<ShapeableImageView>(R.id.ToolImage)
        val nameTool = rootView.findViewById<TextView>(R.id.toolName)
        val descriptionTool = rootView.findViewById<TextView>(R.id.toolDescription)
        val princeTool = rootView.findViewById<TextView>(R.id.toolPrice)
        val countTool = rootView.findViewById<TextView>(R.id.TextnumberOfTools)

        //val days = rootView.find ViewById<EditText>(R.id.numberOfDays).toString()
        //val totalPrice = rootView.findViewById<TextView>(R.id.fullValueOfTool)
        //val priceOneTool = princeTool.toString()


        imageTool.setImageResource(image)
        nameTool.text = "La herraimenta se llama: $name"
        descriptionTool.text = description
        princeTool.text = "La herramienta cuesta: $price"
        countTool.text = "Cantidad de herramientas: $count"

        /*if(days != null){
            val total = days.toInt() * priceOneTool.toInt()
            totalPrice.text = "El precio a pagar es: $total"
        }*/
        val retornoTools = rootView.findViewById<Button>(R.id.ContinueButton)
        retornoTools.setOnClickListener {
            if (name != null && description != null) {
                seeDialog(name, description, price, count)
            }
        }
        return rootView
    }

    private fun seeDialog(name: String, description: String, price: Int, count:Int){
        val builder = AlertDialog.Builder(activity)
        val inflater = layoutInflater
        val view = inflater.inflate(R.layout.dialog_personalized, null)
        builder.setView(view)

        val dialog = builder.create()
        dialog.show()

        val btnYes = view.findViewById<LottieAnimationView>(R.id.btnYes)
        btnYes.setOnClickListener {
            Toast.makeText(activity, "Se realizó la operación...", Toast.LENGTH_SHORT).show()
            seeSubDialog()
            dialog.dismiss()

            val fmanager = activity?.supportFragmentManager
            val fmanagertrs = fmanager?.beginTransaction()
            val fragment = PreBill()

            val dataBundle = Bundle()
            dataBundle.putString("nameToolUpdate", name)
            dataBundle.putString("descriptionToolUpdate", description)
            dataBundle.putInt("priceToolUpdate", price)
            dataBundle.putInt("countToolUpdate", count)

            fragment.arguments = dataBundle
            fmanagertrs?.replace(R.id.toolDescriptionUser, fragment)
            fmanagertrs?.addToBackStack(null)
            fmanagertrs?.commit()

        }

        val btnNo = view.findViewById<LottieAnimationView>(R.id.btnNo)
        btnNo.setOnClickListener {
            Toast.makeText(activity, "Se canceló la operación...", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
            val intent = Intent(activity, ReciclerViewToolUserActivity::class.java)
            startActivity(intent)

        }
    }

    private fun seeSubDialog() {
        val builderSubDialog = AlertDialog.Builder(activity)
            builderSubDialog.setTitle("¿Estas seguro?")
            builderSubDialog.setPositiveButton("Si") {dialogInterface, which ->
                Toast.makeText(activity, "Se realiza la acción", Toast.LENGTH_SHORT).show()
                dialogInterface.dismiss()
                //fragment
            }
            builderSubDialog.setNegativeButton("No") {dialogInterface, which ->
                Toast.makeText(activity, "Se cancela la acción", Toast.LENGTH_SHORT).show()
                dialogInterface.dismiss()
            }
    }
}