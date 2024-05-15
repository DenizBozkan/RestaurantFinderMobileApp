package com.example.restaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NameAdapter(private val nameList:ArrayList<Names>) : RecyclerView.Adapter<NameAdapter.NameViewHolder>() {

    var onItemClick : ((Names) -> Unit)? = null
    class NameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageView: ImageView = itemView.findViewById(R.id.img_dish)
        val textView : TextView = itemView.findViewById(R.id.tv_dish_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_main_category,parent,false)
        return NameViewHolder(view)
    }

    override fun getItemCount(): Int {
        return nameList.size
    }

    override fun onBindViewHolder(holder: NameViewHolder, position: Int) {
        val name = nameList[position]
        holder.imageView.setImageResource(name.image)
        holder.textView.text = name.name

        holder.itemView.setOnClickListener{
            onItemClick?.invoke(name)
        }
    }
}