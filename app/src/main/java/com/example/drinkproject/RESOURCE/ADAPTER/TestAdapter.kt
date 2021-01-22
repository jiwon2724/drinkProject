package com.example.drinkproject.RESOURCE.ADAPTER

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.RecyclerView
import com.example.drinkproject.R

class testAdapter(val context : Context, val list : ArrayList<String>) : RecyclerView.Adapter<testAdapter.Holder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.test_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val view3 = itemView.findViewById<View>(R.id.view3)

        fun bind(){
            when(adapterPosition%2==1){
                true -> { view3.isInvisible = true }
            }
        }
    }
}