package com.example.drinkproject.RESOURCE.ADAPTER

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.drinkproject.R
import kotlinx.android.synthetic.main.activity_drink_main.*
import me.relex.circleindicator.CircleIndicator3


class TopViewpagerAdapter(val context : Context, val image : ArrayList<Int>, val viewpager2 : ViewPager2) : RecyclerView.Adapter<TopViewpagerAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.top_viewpager_items, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(image[position])

        if(position == image.size-2){
            viewpager2.post(runnable)
        }
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

    val runnable = Runnable {
        image.addAll(image)
        notifyDataSetChanged()
    }
}