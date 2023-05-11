package com.uco.pdm.toolplus.vista.toolDescription

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieAnimationView
import com.uco.pdm.toolplus.R
import com.uco.pdm.toolplus.databinding.FragmentToolDescriptionBinding
import com.uco.pdm.toolplus.models.Compra
import com.uco.pdm.toolplus.models.Usuario
import com.uco.pdm.toolplus.persistence.database.AppDatabase
import com.uco.pdm.toolplus.vista.prebill.pre_bill_2
import com.uco.pdm.toolplus.vista.recyclerViewUser.ReciclerViewToolUserActivity

class ToolDescription : Fragment() {

    private var _binding: FragmentToolDescriptionBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("MissingInflatedId", "SetTextI18n", "CutPasteId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentToolDescriptionBinding.inflate(inflater, container, false)
        val dataBundle = arguments
        //val image = dataBundle!!.getInt("imageView")
        val name = dataBundle!!.getString("nameToolUpdate")
        val description = dataBundle.getString("descriptionToolUpdate")
        val price = dataBundle.getInt("priceToolUpdate")
        val count = dataBundle.getInt("countToolUpdate")

        //val imageTool = binding.root.findViewById<ShapeableImageView>(R.id.ToolImage)
        val nameTool = binding.root.findViewById<TextView>(R.id.toolName)
        val descriptionTool = binding.root.findViewById<TextView>(R.id.toolDescription)
        val princeTool = binding.root.findViewById<TextView>(R.id.toolPrice)
        val countTool = binding.root.findViewById<TextView>(R.id.TextNumberOfTools)

        val cantidad = binding.root.findViewById<EditText>(R.id.cantidadDescription)
        val totalPrice = binding.root.findViewById<TextView>(R.id.fullValueOfTool)
        //val priceTotal = days.text.toString().toInt() * price.toString().toInt()


        //imageTool.setImageResource(image)
        nameTool.text = name
        descriptionTool.text = description
        princeTool.text = price.toString()
        countTool.text = count.toString()

        if (cantidad.text.toString().isNotEmpty() && price.toString().isNotEmpty()) {
            val cantidadInt = binding.cantidadDescription.text.toString().toInt()
            val priceInt = binding.toolPrice.text.toString().toInt()
            val priceTotal = cantidadInt * priceInt
            totalPrice.text = priceTotal.toString()
        } else {
            Toast.makeText(activity, "Ingrese los dias de alquiler", Toast.LENGTH_SHORT).show()
        }



        val buiderDialog = AlertDialog.Builder(activity)
        db = AppDatabase.getInstance(context)
        val preBill = binding.root.findViewById<Button>(R.id.ContinueButtonDescription)
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

        val returnRecycler = binding.root.findViewById<Button>(R.id.backButtonDescription)
        returnRecycler.setOnClickListener {
            val intent = Intent(activity, ReciclerViewToolUserActivity::class.java)
            startActivity(intent)
        }
        return binding.root
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

            val dataBundle = arguments
            val idTool = dataBundle!!.getInt("idTool")
            val emailUser = dataBundle.getString("emailCliente")

            val compra = Compra(
                correo = emailUser,
                herramienta = idTool
            )

            db.compraDAO().insertAll(compra)
            Toast.makeText(context, "Compra actualizada ", Toast.LENGTH_LONG).show()


            //fragment
            val fmanager = activity?.supportFragmentManager
            val fmanagertrs = fmanager?.beginTransaction()
            val fragment = pre_bill_2()

            val dataBundlePreBill = Bundle()
            dataBundlePreBill.putInt("cantidad", binding.cantidadDescription.text.toString().toInt())
            dataBundlePreBill.putInt("precio", binding.toolPrice.text.toString().toInt())

            //Toast.makeText(activity, "Valores " + binding.cantidadDescription.text.toString().toInt()
            //        + " "+ binding.toolPrice.text.toString().toInt(), Toast.LENGTH_SHORT).show()


            fragment.arguments = dataBundlePreBill
            fmanagertrs?.add(R.id.toolDescriptionUser, fragment)?.commit()
        }

        val btnNo = view.findViewById<LottieAnimationView>(R.id.btnNo)
        btnNo.setOnClickListener {
            Toast.makeText(activity, "Se cancela prefactura", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
    }

}