package com.uco.pdm.toolplus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.uco.pdm.toolplus.adapters.ToolAdapter
import com.uco.pdm.toolplus.adapters.ToolAdapterUser
import com.uco.pdm.toolplus.databinding.ActivityReciclerViewToolUserBinding
import com.uco.pdm.toolplus.databinding.ActivityRecyclerViewToolsBinding
import com.uco.pdm.toolplus.models.Tool
import com.uco.pdm.toolplus.models.ToolUser
import java.text.FieldPosition

class ReciclerViewToolUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReciclerViewToolUserBinding
    val tool = arrayListOf<ToolUser>()
    var dataUser = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReciclerViewToolUserBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_recicler_view_tool_user)
        setContentView(binding.root)
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
        dataBundle.putInt("imageView", tool[position].imageUser)
        dataBundle.putString("nameToolUpdate", tool[position].nameToolUser)
        dataBundle.putString("descriptionToolUpdate", tool[position].descriptionUser)
        dataBundle.putInt("priceToolUpdate", tool[position].priceUser)
        dataBundle.putInt("countToolUpdate", tool[position].countUser)

        fragment.arguments = dataBundle
        fmanagertrs.add(R.id.elementsRecyclerViewUser, fragment).commit()
    }

    fun retornoUsers(view: View){
        val intent = Intent(this, FirstActivity::class.java)
        startActivity(intent)
    }

    fun initTools(tools:ArrayList<ToolUser>){
        val tool = ToolUser(
            "Lapiz",
            "Tipo 2, color amarillo",
            5,
            35000,
            R.drawable.baseline_edit
        )

        val tool2 = ToolUser(
            "Martillo",
            "Martillo de mano, no muy grande",
            3,
            23000,
            R.drawable.martillo
        )

        val tool3 = ToolUser(
            "Alicate",
            "Para tuercas",
            7,
            43000,
            R.drawable.alicate
        )

        val tool4 = ToolUser(
            "Pulidora",
            "9 pulgadas a 110 voltios, para todo tipo de disco",
            19,
            120000,
            R.drawable.pulidora
        )

        val tool5 = ToolUser(
            "Motosierra",
            "Para cortas grandes troncos o madera",
            6,
            279000,
            R.drawable.motosierra
        )

        val tool6 = ToolUser(
            "Taladro",
            "Para perforar cualquier tipo de pared de media pulgada",
            9,
            20000,
            R.drawable.taladro
        )

        val listTool = arrayOf(
            tool,
            tool2,
            tool3,
            tool4,
            tool5,
            tool6
        )
        listTool.forEach {
            println("se agregan las siguientes herramientas $it")
        }

        tools.addAll(listTool)
        println("AGREGADOS")

    }
}