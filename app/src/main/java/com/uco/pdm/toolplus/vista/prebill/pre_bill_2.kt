package com.uco.pdm.toolplus.vista.prebill

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.uco.pdm.toolplus.R
import com.uco.pdm.toolplus.integration.PriceImpl

class pre_bill_2 : Fragment() {

    private lateinit var priceImpl: PriceImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView : View = inflater.inflate(R.layout.fragment_pre_bill_2, container, false)

        val dataBundle = arguments
        val cantidad = dataBundle!!.getInt("cantidad")
        val precio = dataBundle!!.getInt("precio")

        val total = rootView.findViewById<TextView>(R.id.totalValue)
        val impuesto = rootView.findViewById<EditText>(R.id.impuestoEdit)


        val buttomConfirm = rootView.findViewById<Button>(R.id.buttonContinue)
        buttomConfirm.setOnClickListener {
            val call = priceImpl.operatePrice(cantidad, impuesto.text.toString(),precio).resultado
            total.text = call.toString();
        }
        return rootView
    }
}