package com.uco.pdm.toolplus.vista.recyclerViewUser

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.lottie.LottieAnimationView
import com.uco.pdm.toolplus.*
import com.uco.pdm.toolplus.adapters.ToolAdapter
import com.uco.pdm.toolplus.databinding.ActivityRecyclerViewToolsBinding
import com.uco.pdm.toolplus.vista.firstActivity.FirstActivity
import com.uco.pdm.toolplus.RegisterToolFragment
import com.uco.pdm.toolplus.UpdateToolFragment
import com.uco.pdm.toolplus.models.Herramientas
import com.uco.pdm.toolplus.persistence.database.AppDatabase

class RecyclerViewToolsActivity : AppCompatActivity() {

    private lateinit var db: AppDatabase

    private lateinit var binding: ActivityRecyclerViewToolsBinding
    val tool = arrayListOf<Herramientas>()
    var dataUser = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewToolsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = AppDatabase.getInstance(this)
        initTools(tool)
        buildRecyclerview()
        dataUser = intent.getStringExtra("EMAIL").toString()
        Toast.makeText(this, "Email del usuario: " + dataUser, Toast.LENGTH_LONG).show()
    }


    fun buildRecyclerview(){
        val viewManager = LinearLayoutManager(this.applicationContext)
        val viewAdapter = applicationContext?.let { ToolAdapter(tool, it) }
        binding.toolsAdminRecyclerView.layoutManager = viewManager
        binding.toolsAdminRecyclerView.adapter = viewAdapter
        viewAdapter?.setOnItemClickListener(object : ToolAdapter.OnbuttonClickListener{
            override fun onButtonClickEdit(position: Int) {
                println("dato: " + position)
                editar(position)
            }

            override fun onButtonClickDelete(position: Int) {
                println("dato: " + position)
                eliminar(position)
            }
        })
    }
    fun editar(position: Int){
        val fmanager = supportFragmentManager
        val fmanagertrs = fmanager.beginTransaction()
        val fragment = UpdateToolFragment()

        val dataBundle = Bundle()
        dataBundle.putInt("idTool", tool[position].herramientaId)
        dataBundle.putString("imageView", tool[position].url)
        dataBundle.putString("nameToolUpdate", tool[position].nombre)
        dataBundle.putString("descriptionToolUpdate", tool[position].description)
        tool[position].precio?.let { dataBundle.putInt("priceToolUpdate", it) }
        tool[position].cantidad?.let { dataBundle.putInt("countToolUpdate", it) }
        dataBundle.putString("email", dataUser)

        fragment.arguments = dataBundle
        fmanagertrs.add(R.id.elementsRecyclerView, fragment).commit()
    }

    fun retorno(view: View){
        val intent = Intent(this, FirstActivity::class.java)
        startActivity(intent)
    }

    fun addMoreTool(view: View){
        val fmanager = supportFragmentManager
        val fmanagertrs = fmanager.beginTransaction()
        val fragment = RegisterToolFragment()
        fmanagertrs.add(R.id.elementsRecyclerView, fragment).commit()
    }

    fun eliminar(position: Int){
        db.herramientaDAO().deleteAll(tool[position].herramientaId)
        seeDialog()
    }

    fun seeDialog(){
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val view = inflater.inflate(R.layout.dialog_personalized, null)
        builder.setView(view)

        val dialog = builder.create()
        dialog.show()

        val btnYes = view.findViewById<LottieAnimationView>(R.id.btnYes)
        btnYes.setOnClickListener {
            Toast.makeText(this.applicationContext, "Se eliminó", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        val btnNo = view.findViewById<LottieAnimationView>(R.id.btnNo)
        btnNo.setOnClickListener {
            Toast.makeText(this.applicationContext, "Se cancela operación", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
    }


    fun initTools(tools:ArrayList<Herramientas>){

        //val listAllTools: MutableList<Herramientas> = ArrayList()
        val listAllTools: List<Herramientas> = db.herramientaDAO().getAll()
        tools.addAll(listAllTools)
        println("AGREGADOS")

    }
}