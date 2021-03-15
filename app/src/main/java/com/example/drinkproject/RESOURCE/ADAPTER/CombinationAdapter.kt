package com.example.drinkproject.RESOURCE.ADAPTER

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.drinkproject.R
import com.example.drinkproject.RESOURCE.LISTCLASS.combination
import com.example.drinkproject.databinding.BestCombinationRecyclerviewItemsBinding

class CombinationAdapter(val context : Context, val combinationList : ArrayList<combination>) : RecyclerView.Adapter<CombinationAdapter.Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CombinationAdapter.Holder {
        val binding = BestCombinationRecyclerviewItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: CombinationAdapter.Holder, position: Int) {
        holder.bind(combinationList[position])
    }

    override fun getItemCount(): Int {
        return combinationList.size
    }

    inner class Holder(binding : BestCombinationRecyclerviewItemsBinding) : RecyclerView.ViewHolder(binding.root){
        val rankingImageView = binding.rankingImageView
        val rankingTextView = binding.rankingTextView
        val userIdTextView = binding.helloTextView
        val menuTitleTextView = binding.menuTitleTextView
        val menuDescriptionTextView = binding.menuDescriptionTextView
        val commentTextView = binding.commentTextView
        val thumbsTextView = binding.thumbsTextView


        fun bind(combinationList : combination){
            Glide.with(context).load(combinationList.image).into(rankingImageView)
            //rankingImageView.setImageResource(combinationList.image!!)
            rankingTextView.text = combinationList.ranking.toString()
            userIdTextView.text = combinationList.userId.toString()
            menuTitleTextView.text = combinationList.menuTitle.toString()
            menuDescriptionTextView.text = combinationList.description.toString()
            commentTextView.text = combinationList.comment.toString()
            thumbsTextView.text = combinationList.ranking.toString()
        }
    }
}