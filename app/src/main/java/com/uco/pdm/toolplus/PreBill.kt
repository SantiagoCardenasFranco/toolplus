package com.uco.pdm.toolplus

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.imageview.ShapeableImageView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PreBill.newInstance] factory method to
 * create an instance of this fragment.
 */
class PreBill : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView : View = inflater.inflate(R.layout.fragment_pre_bill, container, false)

        /*val dataBundle = arguments
        val name = dataBundle!!.getString("nameToolUpdate")
        val description = dataBundle.getString("descriptionToolUpdate")
        val price = dataBundle.getInt("priceToolUpdate")
        val count = dataBundle.getInt("countToolUpdate")

        val nameTool = rootView.findViewById<TextView>(R.id.toolName)
        val descriptionTool = rootView.findViewById<TextView>(R.id.toolDescriptionPreBill)
        val princeTool = rootView.findViewById<TextView>(R.id.toolPrice)
        val countTool = rootView.findViewById<TextView>(R.id.TextnumberOfToolsPreBill)

        nameTool.text = "La herraimenta se llama: $name"
        descriptionTool.text = description
        princeTool.text = "La herramienta cuesta: $price"
        countTool.text = "Cantidad de herramientas: $count"*/

        return rootView
    }

}