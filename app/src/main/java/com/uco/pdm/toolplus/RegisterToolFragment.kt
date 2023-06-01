package com.uco.pdm.toolplus

import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.uco.pdm.toolplus.databinding.FragmentRegisterToolBinding
import com.uco.pdm.toolplus.databinding.FragmentSevenRegUserBinding
import com.uco.pdm.toolplus.models.Herramientas
import com.uco.pdm.toolplus.models.Usuario
import com.uco.pdm.toolplus.persistence.database.AppDatabase
import com.uco.pdm.toolplus.vista.firstActivity.FirstActivity
import com.uco.pdm.toolplus.vista.recyclerViewUser.RecyclerViewToolsActivity

class RegisterToolFragment : Fragment() {

    private var _binding: FragmentRegisterToolBinding? = null
    val dbFirebase = Firebase.firestore

    private val binding get() = _binding!!
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterToolBinding.inflate(inflater, container, false)

        // Inflate the layout for this fragment
        val retornoTools = binding.root.findViewById<FloatingActionButton>(R.id.cancelRegister)
        retornoTools.setOnClickListener {
            seeDialog()
        }

        val saveTool = binding.root.findViewById<FloatingActionButton>(R.id.addRegister)
        saveTool.setOnClickListener{

            saveToolFun()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        db = AppDatabase.getInstance(context)
        binding.addRegister.setOnClickListener {
            val herramienta = Herramientas(
                binding.nameToolRegister.text.toString(),
                binding.descriptionToolRegister.text.toString(),
                binding.priceToolRegister.text.toString().toInt(),
                binding.countToolRegister.text.toString().toInt(),
                "https://firebasestorage.googleapis.com/v0/b/toolplus-b5f28.appspot.com/o/alicate.png?alt=media&token=56eef387-0453-4cc8-8042-8c0aab00140e&_gl=1*kd5aj6*_ga*MTk1MTI3NTk1MC4xNjg0Mjg5MTI4*_ga_CW55HF8NVT*MTY4NTQ5OTAyOC42LjEuMTY4NTUwMDU0My4wLjAuMA.."
            )
            db.herramientaDAO().insertAll(herramienta)
            Toast.makeText(context, "Herramienta creada", Toast.LENGTH_LONG).show()

            dbFirebase.collection("herramientas")
                .add(herramienta)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }
        }

    }

    private fun saveToolFun(){
        db = AppDatabase.getInstance(context)
        val herramienta = Herramientas(

            binding.nameToolRegister.text.toString(),
            binding.descriptionToolRegister.text.toString(),
            Integer.parseInt(binding.priceToolRegister.text.toString()),
            Integer.parseInt(binding.countToolRegister.text.toString()),
            binding.imageView.toString()
        )
        db.herramientaDAO().insertAll(herramienta)
        Toast.makeText(context, "Herrameinta creado", Toast.LENGTH_LONG).show()

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