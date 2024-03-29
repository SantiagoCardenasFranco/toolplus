package com.uco.pdm.toolplus.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uco.pdm.toolplus.R
import com.uco.pdm.toolplus.models.Herramientas

class ToolAdapterUser(
    private val dataset: ArrayList<Herramientas>,
    val context: Context

) : RecyclerView.Adapter<ToolAdapterUser.ViewHolder>() {
    private lateinit var mlistener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mlistener = listener
    }

    class ViewHolder(view: View, listener: OnItemClickListener) : RecyclerView.ViewHolder(view) {

        val imageTool: ImageView
        val nameTool: TextView
        val count: TextView
        val price: TextView
        val description: TextView

        // R resourses -> Accesos a las carpetas RES
        init {
            imageTool = view.findViewById(R.id.imageToolUser)
            nameTool = view.findViewById(R.id.nameToolUser)
            count = view.findViewById(R.id.countUser)
            price = view.findViewById(R.id.priceUser)
            description = view.findViewById(R.id.descriptionUser)

            //Control de eventos
            view.setOnClickListener { listener.onItemClick(bindingAdapterPosition) }
        }


    }

    //AttachToRoot

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.one_line_tool_user, parent, false)
        return ViewHolder(view, mlistener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //holder.imageTool.setImageResource(dataset[position].imageUser)
        holder.nameTool.text = dataset[position].nombre
        holder.count.text = "Unidades: "+dataset[position].cantidad.toString()
        holder.price.text = "Costo: "+dataset[position].precio.toString()
        holder.description.text = dataset[position].description
    }

    override fun getItemCount(): Int = dataset.size

}