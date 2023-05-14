package com.uco.pdm.toolplus.vista.prebill

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.uco.pdm.toolplus.R
import com.uco.pdm.toolplus.integration.APIImpl
import kotlinx.coroutines.*

class pre_bill_2 : Fragment() {

    private lateinit var apiImpl: APIImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView : View = inflater.inflate(R.layout.fragment_pre_bill_2, container, false)

        apiImpl = APIImpl()

        val dataBundle = arguments
        val cantidad = dataBundle!!.getInt("cantidad")
        val precio = dataBundle!!.getInt("precio")

        Toast.makeText(activity, "Valores " + cantidad + " "+ precio, Toast.LENGTH_SHORT).show()

        val total = rootView.findViewById<TextView>(R.id.totalValue)
        val impuesto = rootView.findViewById<EditText>(R.id.impuestoEdit)


        val buttomConfirm = rootView.findViewById<Button>(R.id.buttonContinue)
        buttomConfirm.setOnClickListener {
            GlobalScope.launch (Dispatchers.IO ){
                val dataAut = apiImpl.operateAut("prueba", "prueba").token
                val call = apiImpl.operatePrice(cantidad, impuesto.text.toString(),precio, dataAut).resultado.toString()
                withContext(Dispatchers.Main) {
                    total.text = call;
                }
            }
        }
        return rootView
    }
}