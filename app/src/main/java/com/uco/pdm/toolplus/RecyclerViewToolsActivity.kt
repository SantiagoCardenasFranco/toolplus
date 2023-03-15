package com.uco.pdm.toolplus

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.util.AttributeSet
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
        viewAdapter?.setOnItemClickListener(object :ToolAdapter.OnItemClickListener{
            override fun onItemClick(position: Int) {
                println("clic sobre item "+ position)
                }
            }
        )
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
            R.drawable.baseline_edit
        )

        val tool3 = Tool(
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