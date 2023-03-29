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

    @SuppressLint("MissingInflatedId", "SetTextI18n", "CutPasteId")
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
        val countTool = rootView.findViewById<TextView>(R.id.TextNumberOfTools)

        //val days = rootView.find ViewById<EditText>(R.id.numberOfDays).toString()
        //val totalPrice = rootView.findViewById<TextView>(R.id.fullValueOfTool)
        //val priceOneTool = princeTool.toString()


        imageTool.setImageResource(image)
        nameTool.text = name
        descriptionTool.text = description
        princeTool.text = price.toString()
        countTool.text = count.toString()

        /*if(days != null){
            val total = days.toInt() * priceOneTool.toInt()
            totalPrice.text = "El precio a pagar es: $total"
        }*/
        val buiderDialog = AlertDialog.Builder(activity)

        val preBill = rootView.findViewById<Button>(R.id.ContinueButtonDescription)
        preBill.setOnClickListener {
                buiderDialog.setTitle("¿Desea realizar más compras?")
                    .setPositiveButton("Continuar"){dialogInterface, it ->
                        dialogInterface.dismiss()
                        seeDialog()
                    }
                    .setNegativeButton("No comprar"){dialogInterface, it ->
                        dialogInterface.dismiss()
                        val intent = Intent(activity, ReciclerViewToolUserActivity::class.java)
                        startActivity(intent)
                    }
                    .setNeutralButton("Cancelar"){dialogInterface, it ->
                        dialogInterface.dismiss()
                        Toast.makeText(activity, "Se cancela comprar del producto", Toast.LENGTH_SHORT).show()
                    }
                    .show()
                }

        val returnRecycler = rootView.findViewById<Button>(R.id.backButtonDescription)
        returnRecycler.setOnClickListener {
            val intent = Intent(activity, ReciclerViewToolUserActivity::class.java)
            startActivity(intent)
        }
        return rootView
    }

    fun seeDialog(){
        val builder = AlertDialog.Builder(activity)
        val inflater = layoutInflater
        val view = inflater.inflate(R.layout.dialog_personalized, null)
        builder.setView(view)

        val dialog = builder.create()
        dialog.show()

        val btnYes = view.findViewById<LottieAnimationView>(R.id.btnYes)
        btnYes.setOnClickListener {
            Toast.makeText(activity, "Se realizará prefactura", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
            //fragment
            val fmanager = activity?.supportFragmentManager
            val fmanagertrs = fmanager?.beginTransaction()
            val fragment = pre_bill_2()
            fmanagertrs?.add(R.id.toolDescriptionUser, fragment)?.commit()
        }

        val btnNo = view.findViewById<LottieAnimationView>(R.id.btnNo)
        btnNo.setOnClickListener {
            Toast.makeText(activity, "Se cancela prefactura", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
    }

}