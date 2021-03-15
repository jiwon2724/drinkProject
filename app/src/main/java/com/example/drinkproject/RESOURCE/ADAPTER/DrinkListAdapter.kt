package com.example.drinkproject.RESOURCE.ADAPTER

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.RecyclerView
import com.example.drinkproject.R

class DrinkListAdapter(val context : Context, val list : ArrayList<String>, val chooseLayout : Int) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    companion object {
        const val GRID_TYPE = 0
        const val LIST_TYPE = 1
    }

    override fun getItemViewType(position: Int): Int {
        return when(chooseLayout){
            0 -> GRID_TYPE
            1 -> LIST_TYPE
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            GRID_TYPE -> {
                val gridView = LayoutInflater.from(context).inflate(R.layout.drink_grid_view, parent, false)
                GridHolder(gridView)
            }
            LIST_TYPE -> {
                val listView = LayoutInflater.from(context).inflate(R.layout.drink_list_view, parent, false)
                ListHolder(listView)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is GridHolder -> holder.bind()
            is ListHolder -> holder.bind()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class GridHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val view3 = itemView.findViewById<View>(R.id.view3)

        fun bind(){
            when(bindingAdapterPosition%2==1){
                true -> { view3.isInvisible = true }
            }
        }
    }

    inner class ListHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bind() {

        }
    }
}