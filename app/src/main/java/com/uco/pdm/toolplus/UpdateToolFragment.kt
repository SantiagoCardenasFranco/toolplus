package com.uco.pdm.toolplus

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.imageview.ShapeableImageView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.uco.pdm.toolplus.databinding.FragmentRegisterToolBinding
import com.uco.pdm.toolplus.databinding.FragmentUpdateToolBinding
import com.uco.pdm.toolplus.models.Herramientas
import com.uco.pdm.toolplus.persistence.database.AppDatabase
import com.uco.pdm.toolplus.vista.recyclerViewUser.RecyclerViewToolsActivity

class UpdateToolFragment : Fragment() {

    private var _binding: FragmentUpdateToolBinding? = null
    val dbFirebase = Firebase.firestore


    private val binding get() = _binding!!
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateToolBinding.inflate(inflater, container, false)

        val dataBundle = arguments
        val image = dataBundle!!.getInt("imageView")
        val name = dataBundle!!.getString("nameToolUpdate")
        val description = dataBundle.getString("descriptionToolUpdate")
        val price = dataBundle.getInt("priceToolUpdate")
        val count = dataBundle.getInt("countToolUpdate")
        val user = dataBundle.getString("email")
        //Toast.makeText(activity, "Email del usuario: " + user, Toast.LENGTH_LONG).show()


        val imageTool = binding.root.findViewById<ShapeableImageView>(R.id.imageViewTool)
        val nameTool = binding.root.findViewById<EditText>(R.id.nameToolUpdate)
        val descriptionTool = binding.root.findViewById<EditText>(R.id.descriptionToolUpdate)
        val princeTool = binding.root.findViewById<EditText>(R.id.priceToolUpdate)
        val countTool = binding.root.findViewById<EditText>(R.id.countToolUpdate)
        imageTool.setImageResource(image)
        nameTool.setText(name)
        descriptionTool.setText(description)
        princeTool.setText(price.toString())
        countTool.setText(count.toString())

        val retornoTools = binding.root.findViewById<FloatingActionButton>(R.id.cancelUpdate)
        retornoTools.setOnClickListener {
            seeDialog()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        val dataBundle = arguments
        val idTool = dataBundle!!.getInt("idTool")
        db = AppDatabase.getInstance(context)
        binding.addUpdate.setOnClickListener {
            val herramienta = Herramientas(
                binding.nameToolUpdate.text.toString(),
                binding.descriptionToolUpdate.text.toString(),
                binding.priceToolUpdate.text.toString().toInt(),
                binding.countToolUpdate.text.toString().toInt(),
                binding.imageViewTool.textAlignment.toString()
            )

            val nuevosDatos = hashMapOf(
                "nombre" to binding.nameToolUpdate.text.toString(),
                "description" to binding.descriptionToolUpdate.text.toString(),
                "precio" to binding.priceToolUpdate.text.toString().toInt(),
                "cantidad" to binding.countToolUpdate.text.toString().toInt(),
                "url" to "",
            )

            dbFirebase.collection("herramientas")
                .document(idTool.toString())
                .update(nuevosDatos as Map<String, Any>)
                .addOnSuccessListener {
                    Log.d(TAG, "Herramienta actualizada correctamente")
                }
                .addOnFailureListener { exception ->
                    Log.w(TAG, "Error al actualizar la herramienta", exception)
                }

            herramienta.cantidad?.let { it1 ->
                herramienta.nombre?.let { it2 ->
                    herramienta.description?.let { it3 ->
                        herramienta.precio?.let { it4 ->
                            db.herramientaDAO().update(
                                it2, it3, it4,
                                it1, herramienta.url, idTool)
                        }
                    }
                }
            }
            Toast.makeText(context, "Herramienta actualizada " + idTool, Toast.LENGTH_LONG).show()
        }
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
            btnYes.setAnimation(R.raw.confirmed_tick)
            btnYes.playAnimation()
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