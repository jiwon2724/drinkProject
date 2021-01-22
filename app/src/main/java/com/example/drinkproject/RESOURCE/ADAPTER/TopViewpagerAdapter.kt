package com.example.drinkproject.RESOURCE.ADAPTER

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.drinkproject.R

class TopViewpagerAdapter(val context : Context, val image : ArrayList<Int>) : RecyclerView.Adapter<TopViewpagerAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.top_viewpager_items, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(image[position])
    }

    override fun getItemCount(): Int {
        return image.size
    }

    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val image = itemView.findViewById<ImageView>(R.id.viewpagerImageView)

        fun bind(img : Int){
            Glide.with(context).load(img).into(image)
        }
    }
}