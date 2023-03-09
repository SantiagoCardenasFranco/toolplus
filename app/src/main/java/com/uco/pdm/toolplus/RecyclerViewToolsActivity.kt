package com.uco.pdm.toolplus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import androidx.recyclerview.widget.LinearLayoutManager
import com.uco.pdm.toolplus.adapters.ToolAdapter
import com.uco.pdm.toolplus.databinding.ActivityRecyclerViewToolsBinding
import com.uco.pdm.toolplus.models.Tool

class RecyclerViewToolsActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityRecyclerViewToolsBinding
    private val binding get() = _binding!!
    private lateinit var adapter : ToolAdapter
    val tool = arrayListOf<Tool>()
    override fun onCreate(savedInstanceState: Bundle?) {
        _binding = ActivityRecyclerViewToolsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_tools)
        initTools(tool)
        buildRecyclerview()
    }

    fun buildRecyclerview(){
        val viewManager = LinearLayoutManager(this.applicationContext)
        val viewAdapter = applicationContext?.let { ToolAdapter(tool, it) }
        binding.toolsAdminRecyclerView.layoutManager = viewManager
        binding.toolsAdminRecyclerView.adapter = viewAdapter
    }

    fun initTools(tools:ArrayList<Tool>){
        val tool = Tool(
            "Destornillador",
            "Destornillador tipo cruz",
            5,
            35000
        )

        val tool2 = Tool(
            "Martillo",
            "Martillo de mano, no muy grande",
            3,
            23000
        )

        val tool3 = Tool(
            "Alicate",
            "Para tuercas",
            7,
            43000
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