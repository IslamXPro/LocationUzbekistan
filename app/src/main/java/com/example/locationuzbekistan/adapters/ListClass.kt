package com.example.locationuzbekistan.adapters

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.locationuzbekistan.R
import com.example.locationuzbekistan.Utils.InternetData.list
import java.io.File

class ListClass(val context: Context, var fileNameList: List<String> /*var fileDoneList: List<String>*/) :
    RecyclerView.Adapter<ListClass.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.single_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fileName = fileNameList[position]
        holder.fileName.text = fileName
        //check upload image
        /*val fileDone = fileDoneList[position]
        if (fileDone == "Uploading") {
            holder.fileDone.setImageResource(R.drawable.progress)
        } else {
            holder.fileDone.setImageResource(R.drawable.checked)
        }*/
        Glide.with(context).load(fileNameList[position]).into(holder.image)
    }

    override fun getItemCount(): Int {
        return fileNameList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var fileName: TextView
   //     var fileDone: ImageView
        lateinit var image: ImageView

        init {
            fileName = itemView.findViewById(R.id.txtFilename)
     //       fileDone = itemView.findViewById(R.id.imgLoading)
            image = itemView.findViewById(R.id.imgUpload)
        }
    }
}