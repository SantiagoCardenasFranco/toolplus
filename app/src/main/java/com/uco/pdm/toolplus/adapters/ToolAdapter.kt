package com.uco.pdm.toolplus.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.uco.pdm.toolplus.R
import com.uco.pdm.toolplus.models.Herramientas

class ToolAdapter(
    private val dataset: ArrayList<Herramientas>,
    val context: Context

    ) : RecyclerView.Adapter<ToolAdapter.ViewHolder>() {
        private lateinit var mlistener: OnbuttonClickListener

        interface OnbuttonClickListener {
            fun onButtonClickEdit(position: Int)
            fun onButtonClickDelete(position: Int)
        }

        fun setOnItemClickListener(listener: OnbuttonClickListener) {
            mlistener = listener
        }

        class ViewHolder(view: View, listener: OnbuttonClickListener) : RecyclerView.ViewHolder(view) {

            val nameTool: TextView
            val imageTool: ImageView
            val count: TextView
            val price: TextView
            val btnEdit: Button
            val btnDelete: Button
            val detail: TextView

            // R resourses -> Accesos a las carpetas RES
            init {
                nameTool = view.findViewById(R.id.nameTool)
                imageTool = view.findViewById(R.id.imageTool)
                count = view.findViewById(R.id.count)
                price = view.findViewById(R.id.price)
                btnEdit = view.findViewById(R.id.btnEdit)
                btnDelete = view.findViewById(R.id.btnDelete)
                detail = view.findViewById(R.id.description)

                //Control de eventos
                btnEdit.setOnClickListener { listener.onButtonClickEdit(bindingAdapterPosition) }
                btnDelete.setOnClickListener {listener.onButtonClickDelete(bindingAdapterPosition)}
                //view.setOnClickListener { listener.onItemClick(bindingAdapterPosition) }
            }


        }

        //AttachToRoot

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.one_line_tool, parent, false)
            return ViewHolder(view, mlistener)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            Picasso.get().load(dataset[position].url).into(holder.imageTool)
            holder.nameTool.text = dataset[position].nombre
            holder.count.text = dataset[position].cantidad.toString()
            holder.price.text = dataset[position].precio.toString()
            holder.detail.text = dataset[position].description
        }

        override fun getItemCount(): Int = dataset.size

    }