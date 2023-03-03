package com.uco.pdm.toolplus.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uco.pdm.toolplus.R
import com.uco.pdm.toolplus.models.Tool

class ToolAdapter (
    private val dataset: ArrayList<Tool>,
    val context: Context

    ) : RecyclerView.Adapter<ToolAdapter.ViewHolder>() {
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
            val btnEdit: Button
            val btnDelete: Button
            val description: TextView

            // R resourses -> Accesos a las carpetas RES
            init {
                imageTool = view.findViewById(R.id.imageTool)
                nameTool = view.findViewById(R.id.nameTool)
                count = view.findViewById(R.id.count)
                price = view.findViewById(R.id.price)
                btnEdit = view.findViewById(R.id.btnEdit)
                btnDelete = view.findViewById(R.id.btnDelete)
                description = view.findViewById(R.id.description)

                //Control de eventos
                view.setOnClickListener { listener.onItemClick(bindingAdapterPosition) }
            }


        }

        //AttachToRoot

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.one_line_tool, parent, false)
            return ViewHolder(view, mlistener)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.nameTool.text = dataset[position].nameTool
            holder.count.text = dataset[position].count
            holder.price.text = dataset[position].price
            holder.price.text = dataset[position].description

        }

        override fun getItemCount(): Int = dataset.size

    }