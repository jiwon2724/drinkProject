package com.example.drinkproject.RESOURCE.ADAPTER

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.drinkproject.R
import com.example.drinkproject.RESOURCE.LISTCLASS.comment

class CommentAdapter(val context : Context, val commentList : ArrayList<String>) : RecyclerView.Adapter<CommentAdapter.Holder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.drink_comment_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(commentList[position])

    }

    override fun getItemCount(): Int {
        return commentList.size
    }

    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        val testName = itemView.findViewById<TextView>(R.id.testName)

        fun bind(title : String){
            testName.text = title
        }

    }
}