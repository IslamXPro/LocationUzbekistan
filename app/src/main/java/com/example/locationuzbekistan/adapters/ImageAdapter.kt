package com.example.locationuzbekistan.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.locationuzbekistan.databinding.ImgItemRvBinding
import com.example.locationuzbekistan.models.ModelClass
import com.squareup.picasso.Picasso

class ImageAdapter (val list: ArrayList<ModelClass>, var myClick: MyClick) :
    RecyclerView.Adapter<ImageAdapter.Vh>() {


    inner class Vh(var itemRv: ImgItemRvBinding) : RecyclerView.ViewHolder(itemRv.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(user: ModelClass, pos: Int) {
            Picasso.get().load(user.getImagename()).into(itemRv.modelImg)
            itemRv.root.setOnClickListener {
                myClick.click(user)
            }

        }
    }

    interface MyClick {
        fun click(user: ModelClass)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ImgItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size

}