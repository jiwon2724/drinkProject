package com.example.drinkproject.RESOURCE.ADAPTER

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.drinkproject.R
import com.example.drinkproject.RESOURCE.LISTCLASS.comment
import com.example.drinkproject.databinding.DrinkCommentItemBinding

class CommentAdapter(val context : Context, val commentList : ArrayList<String>) : RecyclerView.Adapter<CommentAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val bidning = DrinkCommentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(bidning)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(commentList[position])

    }

    override fun getItemCount(): Int {
        return commentList.size
    }

    inner class Holder(binding : DrinkCommentItemBinding) : RecyclerView.ViewHolder(binding.root) {

        val testName = binding.testName

        fun bind(title : String){
            testName.text = title
        }

    }
}