package com.uco.pdm.toolplus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.uco.pdm.toolplus.adapters.ToolAdapter
import com.uco.pdm.toolplus.databinding.ActivityRecyclerViewToolsBinding
import com.uco.pdm.toolplus.models.Tool

class RecyclerViewToolsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerViewToolsBinding
    val tool = arrayListOf<Tool>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewToolsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initTools(tool)
        buildRecyclerview()
    }


    fun buildRecyclerview(){
        val viewManager = LinearLayoutManager(this.applicationContext)
        val viewAdapter = applicationContext?.let { ToolAdapter(tool, it) }
        binding.toolsAdminRecyclerView.layoutManager = viewManager
        binding.toolsAdminRecyclerView.adapter = viewAdapter
        viewAdapter?.setOnItemClickListener(object :ToolAdapter.OnbuttonClickListener{
            override fun onButtonClick(position: Int) {
                println("dato: " + position)
                editar(position)
                eliminar(position)
            }
        })
    }
    fun editar(position: Int){
        val fmanager = supportFragmentManager
        val fmanagertrs = fmanager.beginTransaction()
        val fragment = UpdateToolFragment()

        val dataBundle = Bundle()
        dataBundle.putInt("imageView", tool[position].image)
        dataBundle.putString("nameToolUpdate", tool[position].nameTool)
        dataBundle.putString("descriptionToolUpdate", tool[position].detail)
        dataBundle.putInt("priceToolUpdate", tool[position].price)
        dataBundle.putInt("countToolUpdate", tool[position].count)

        fragment.arguments = dataBundle
        fmanagertrs.add(R.id.elementsRecyclerView, fragment).commit()
    }

    fun retorno(view: View){
        val intent = Intent(this, FirstActivity::class.java)
        startActivity(intent)
    }

    fun eliminar(position: Int){
        val dataTool = tool
        dataTool.removeAt(position)
    }

    fun initTools(tools:ArrayList<Tool>){
        val tool = Tool(
            "Lapiz",
            "Tipo 2, color amarillo",
            5,
            35000,
            R.drawable.baseline_edit
        )

        val tool2 = Tool(
            "Martillo",
            "Martillo de mano, no muy grande",
            3,
            23000,
            R.drawable.martillo
        )

        val tool3 = Tool(
            "Alicate",
            "Para tuercas",
            7,
            43000,
            R.drawable.alicate
        )

        val tool4 = Tool(
            "Pulidora",
            "9 pulgadas a 110 voltios, para todo tipo de disco",
            19,
            120000,
            R.drawable.pulidora
        )

        val tool5 = Tool(
            "Motosierra",
            "Para cortas grandes troncos o madera",
            6,
            279000,
            R.drawable.motosierra
        )

        val tool6 = Tool(
            "Taladro",
            "Para perforar cualquier tipo de pared de media pulgada",
            9,
            20000,
            R.drawable.motosierra
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