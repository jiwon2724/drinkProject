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

class CombinationAdapter(val context : Context, val combinationList : ArrayList<combination>) : RecyclerView.Adapter<CombinationAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CombinationAdapter.Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.best_combination_recyclerview_items, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: CombinationAdapter.Holder, position: Int) {
        holder.bind(combinationList[position])
    }

    override fun getItemCount(): Int {
        return combinationList.size
    }

    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val rankingImageView = itemView.findViewById<ImageView>(R.id.rankingImageView)
        val rankingTextView = itemView.findViewById<TextView>(R.id.rankingTextView)
        val userIdTextView = itemView.findViewById<TextView>(R.id.userIdTextView)
        val menuTitleTextView = itemView.findViewById<TextView>(R.id.menuTitleTextView)
        val menuDescriptionTextView = itemView.findViewById<TextView>(R.id.menuDescriptionTextView)
        val commentTextView = itemView.findViewById<TextView>(R.id.commentTextView)
        val thumbsTextView = itemView.findViewById<TextView>(R.id.thumbsTextView)


        fun bind(combinationList : combination){
            Glide.with(context).load(combinationList.image).into(rankingImageView)
            // rankingImageView.setImageResource(combinationList.image!!)
            rankingTextView.text = combinationList.ranking.toString()
            userIdTextView.text = combinationList.userId.toString()
            menuTitleTextView.text = combinationList.menuTitle.toString()
            menuDescriptionTextView.text = combinationList.description.toString()
            commentTextView.text = combinationList.comment.toString()
            thumbsTextView.text = combinationList.ranking.toString()
        }
    }
}