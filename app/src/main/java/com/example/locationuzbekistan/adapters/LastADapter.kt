package com.example.locationuzbekistan.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.locationuzbekistan.databinding.ImgItemRvBinding
import com.example.locationuzbekistan.databinding.ImgLastRvBinding
import com.example.locationuzbekistan.models.ImageClass

class LastADapter(val context: Context, val fileNameList: ArrayList<String>) :
    RecyclerView.Adapter<LastADapter.Vh>() {


    inner class Vh(var itemRv: ImgItemRvBinding) : RecyclerView.ViewHolder(itemRv.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(user: String, pos: Int) {
            Glide.with(context).load(fileNameList[pos]).into(itemRv.modelImg)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ImgItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(fileNameList[position], position)
    }

    override fun getItemCount(): Int = fileNameList.size
}