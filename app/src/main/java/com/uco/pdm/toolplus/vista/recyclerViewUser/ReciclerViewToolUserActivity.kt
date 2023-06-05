package com.uco.pdm.toolplus.vista.recyclerViewUser

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.uco.pdm.toolplus.R
import com.uco.pdm.toolplus.adapters.ToolAdapterUser
import com.uco.pdm.toolplus.databinding.ActivityReciclerViewToolUserBinding
import com.uco.pdm.toolplus.models.Herramientas
import com.uco.pdm.toolplus.persistence.database.AppDatabase
import com.uco.pdm.toolplus.utils.UtilsArray
import com.uco.pdm.toolplus.vista.firstActivity.FirstActivity
import com.uco.pdm.toolplus.vista.toolDescription.ToolDescription

class ReciclerViewToolUserActivity : AppCompatActivity() {

    private lateinit var db: AppDatabase
    val dbFirebase = Firebase.firestore

    private lateinit var binding: ActivityReciclerViewToolUserBinding
    val tool = arrayListOf<Herramientas>()
    var dataUser = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReciclerViewToolUserBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_recicler_view_tool_user)
        setContentView(binding.root)
        db = AppDatabase.getInstance(this)

        initTools(tool)
        buildRecyclerview()
        dataUser = intent.getStringExtra("EMAIL").toString()
        Toast.makeText(this, "Email del usuario: " + dataUser, Toast.LENGTH_LONG).show()

    }

    fun buildRecyclerview(){
        val viewManager = LinearLayoutManager(this.applicationContext)
        val viewAdapter = applicationContext?.let { ToolAdapterUser(tool, it) }
        binding.toolsUserRecyclerView.layoutManager = viewManager
        binding.toolsUserRecyclerView.adapter = viewAdapter
        viewAdapter?.setOnItemClickListener(object : ToolAdapterUser.OnItemClickListener{
            override fun onItemClick(position: Int) {
                println("clic sobre item "+ position)
                descriptionToolUser(position)
            }
        })
    }

    private fun descriptionToolUser(position: Int){
        val fmanager = supportFragmentManager
        val fmanagertrs = fmanager.beginTransaction()
        val fragment = ToolDescription()
        val dataBundle = Bundle()
        dataBundle.putInt("idTool", tool[position].herramientaId)
        dataBundle.putString("idImage", tool[position].url)
        dataBundle.putString("emailCliente", dataUser)
        dataBundle.putString("imageView", tool[position].url)
        dataBundle.putString("nameToolUpdate", tool[position].nombre)
        dataBundle.putString("descriptionToolUpdate", tool[position].description)
        tool[position].precio?.let { dataBundle.putInt("priceToolUpdate", it) }
        tool[position].cantidad?.let { dataBundle.putInt("countToolUpdate", it) }

        fragment.arguments = dataBundle
        fmanagertrs.add(R.id.elementsRecyclerViewUser, fragment).commit()
    }

    fun retornoUsers(view: View){
        val intent = Intent(this, FirstActivity::class.java)
        startActivity(intent)
    }

    fun initTools(tools:ArrayList<Herramientas>){
        var herramientasList = emptyArray<Herramientas>()

        dbFirebase.collection("herramientas")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(ContentValues.TAG, "${document.id} => ${document.data}")
                    val herramienta = document.toObject<Herramientas>()
                    herramientasList = UtilsArray().append(herramientasList, herramienta)
                }
                db.herramientaDAO().insertAll(*herramientasList)
                herramientasList.forEach { println("Herramientas from #####FIREBASE#### $it") }}
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }

        val listAllToolsLocal: List<Herramientas> = db.herramientaDAO().getAll()
        tools.addAll(listAllToolsLocal)

    }

}