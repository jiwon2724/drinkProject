package com.example.drinkproject.RESOURCE.ADAPTER

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.drinkproject.R
import com.example.drinkproject.RESOURCE.ItemClickListener

class UserInfoAdatper(val context : Context, val list : ArrayList<String>) : RecyclerView.Adapter<ViewHolder>() {
    companion object {
        val BAR = 0
        val TEXT = 1
    }
    lateinit var itemClickListener: ItemClickListener // 인터페이스

    /** 액티비티에서 재정의 **/
    fun setItemClickListner(itemClickListener: ItemClickListener){
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when(viewType){
            BAR -> BarHolder(LayoutInflater.from(context).inflate(R.layout.drink_user_bar_item, parent, false))
            else -> TextHolder(LayoutInflater.from(context).inflate(R.layout.drink_user_text_item, parent, false))
        }
    }

    override fun onBindViewHolder(holder : ViewHolder, position: Int) {
        when(holder){
            is TextHolder -> {
                holder.bind(list[position])
                holder.itemView.setOnClickListener {
                    itemClickListener.onClick(it, position)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return when(list[position]){
            "bar" -> BAR
            else -> TEXT
        }
    }

    inner class BarHolder(itemView : View) : ViewHolder(itemView){

    }

    inner class TextHolder(itemView : View) : ViewHolder(itemView){
        val titleTextView = itemView.findViewById<TextView>(R.id.titleTextView)
        fun bind(title : String){
            titleTextView.text = title
        }
    }


}