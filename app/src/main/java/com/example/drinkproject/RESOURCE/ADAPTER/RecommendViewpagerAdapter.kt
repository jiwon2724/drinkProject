package com.example.drinkproject.RESOURCE.ADAPTER

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.drinkproject.R
import com.example.drinkproject.RESOURCE.LISTCLASS.recommend

class RecommendViewpagerAdapter(val context : Context, val recommendeList : ArrayList<recommend>) : RecyclerView.Adapter<RecommendViewpagerAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.mid_viewpager_items, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(recommendeList[position])
    }

    override fun getItemCount(): Int {
        return recommendeList.size
    }

    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val drinkImageView = itemView.findViewById<ImageView>(R.id.drinkImageView)
        val drinkTitleTextView = itemView.findViewById<TextView>(R.id.drinkTitleTextView)
        val drinkRatingTextView = itemView.findViewById<TextView>(R.id.drinkRatingTextView)
        val drinkAlcolTextView = itemView.findViewById<TextView>(R.id.drinkAlcolTextView)
        val drinkLikeTextView = itemView.findViewById<TextView>(R.id.drinkLikeTextView)

        fun bind(data : recommend){
            drinkImageView.setImageResource(data.image!!)
            drinkTitleTextView.text = data.title
            drinkRatingTextView.text = data.rating.toString()
            drinkAlcolTextView.text = data.alchol.toString() + "%"
            drinkLikeTextView.text = data.heart.toString()
        }
    }

}