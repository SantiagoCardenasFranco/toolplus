package com.uco.pdm.toolplus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.uco.pdm.toolplus.adapters.ToolAdapter
import com.uco.pdm.toolplus.adapters.ToolAdapterUser
import com.uco.pdm.toolplus.databinding.ActivityReciclerViewToolUserBinding
import com.uco.pdm.toolplus.databinding.ActivityRecyclerViewToolsBinding
import com.uco.pdm.toolplus.models.Tool
import com.uco.pdm.toolplus.models.ToolUser

class ReciclerViewToolUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReciclerViewToolUserBinding
    val tool = arrayListOf<ToolUser>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReciclerViewToolUserBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_recicler_view_tool_user)
        setContentView(binding.root)
        initTools(tool)
        buildRecyclerview()
    }

    fun buildRecyclerview(){
        val viewManager = LinearLayoutManager(this.applicationContext)
        val viewAdapter = applicationContext?.let { ToolAdapterUser(tool, it) }
        binding.toolsUserRecyclerView.layoutManager = viewManager
        binding.toolsUserRecyclerView.adapter = viewAdapter
        viewAdapter?.setOnItemClickListener(object : ToolAdapterUser.OnItemClickListener{
            override fun onItemClick(position: Int) {
                println("clic sobre item "+ position)
            }
        })
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
            R.drawable.baseline_edit
        )

        val tool3 = ToolUser(
            "Alicate",
            "Para tuercas",
            7,
            43000,
            R.drawable.baseline_edit
        )

        val listTool = arrayOf(
            tool,
            tool2,
            tool3
        )
        listTool.forEach {
            println("se agregan las siguientes herramientas $it")
        }

        tools.addAll(listTool)
        println("AGREGADOS")

    }
}