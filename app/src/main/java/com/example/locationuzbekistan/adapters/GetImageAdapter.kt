package com.example.locationuzbekistan.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.locationuzbekistan.databinding.GetImageRvBinding
import com.squareup.picasso.Picasso

class GetImageAdapter(var context: Context,val list: ArrayList<String>) :
    RecyclerView.Adapter<GetImageAdapter.Vh>() {


    inner class Vh(var itemRv: GetImageRvBinding) : RecyclerView.ViewHolder(itemRv.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(user: String, pos: Int) {
      //      Picasso.get().load(user.getImagename()).into(itemRv.imgUpload)
        Glide.with(context).load(list[pos]).into(itemRv.imgUpload)

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(GetImageRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size
}