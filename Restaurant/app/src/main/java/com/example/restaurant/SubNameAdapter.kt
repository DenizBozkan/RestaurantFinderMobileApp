package com.example.restaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SubNameAdapter(private val nameList:ArrayList<SubNames>) : RecyclerView.Adapter<SubNameAdapter.SubNameViewHolder>() {
    var onItemClick : ((SubNames) -> Unit)? = null
    class SubNameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageView: ImageView = itemView.findViewById(R.id.img_rest)
        val textView : TextView = itemView.findViewById(R.id.tv_rest_name)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubNameViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sub_category,parent,false)
        return SubNameViewHolder(view)
    }

    override fun getItemCount(): Int {
        return nameList.size
    }

    override fun onBindViewHolder(holder: SubNameViewHolder, position: Int) {
        val name = nameList[position]
        holder.imageView.setImageResource(name.image)
        holder.textView.text = name.name

        holder.itemView.setOnClickListener{
            onItemClick?.invoke(name)
        }
    }
}