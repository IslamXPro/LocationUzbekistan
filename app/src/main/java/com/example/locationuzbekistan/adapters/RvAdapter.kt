package com.example.locationuzbekistan.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.denzcoskun.imageslider.models.SlideModel
import com.example.locationuzbekistan.databinding.ItemRvBinding
import com.example.locationuzbekistan.models.ModelClass
import com.example.locationuzbekistan.models.User
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
import java.io.File


@SuppressLint("StaticFieldLeak","SetTextI18n")
lateinit var fireBaseStore: FirebaseFirestore
private var imagelist = ArrayList<ModelClass>()
lateinit var listimage: ArrayList<SlideModel>
class RvAdapter(val context: Context, val list: ArrayList<User>, var myClick: MyClick) :

    RecyclerView.Adapter<RvAdapter.Vh>() {

    inner class Vh(var itemRv: ItemRvBinding) : RecyclerView.ViewHolder(itemRv.root) {
        @SuppressLint("CheckResult")
        fun onBind(user: User, pos: Int) {
 //           listimage = ArrayList()
/*                    val bitmap = BitmapFactory.decodeFile(user.imagelist!![pos])
                    itemRv.rvImg.setImageBitmap(bitmap)*/
                Picasso.get().load(user.imagelist!![pos]).into(itemRv.rvImg)

 //           Glide.with(context).load(user.imagelist!![0]).into(itemRv.rvImg)
//            listimage.add(SlideModel(user.imagelist!![pos]))
            itemRv.rvPlName.text = user.placeName
//            itemRv.rvImg.setImageList(listimage)
          //  Picasso.get().load(user.image).into(itemRv.rvImg)
  //          fireBaseStore = FirebaseFirestore.getInstance()

        /*    fireBaseStore.collection("user")
                .get()
                .addOnCompleteListener {
                    if (it.isSuccessful){
                        imagelist = ArrayList()
                        val result = it.result
                        result?.forEach { queryDocumentSnapshot ->
                            val user = queryDocumentSnapshot.toObject(User::class.java)
                            user.imagelist?.let { it1 -> imagelist.addAll(it1) }
                        }
                    }
                }*/
            itemRv.root.setOnClickListener {
                myClick.click(user)
            }
            /* MySharedPreference.init(context)
             var index = -1
             val mList = MySharedPreference.adibList
             for (n in mList.indices){
                 if (mList[n].id == user.id){
                     index = n
                     break
                 }
             }
             if (index!=-1){
                 itemRv.likeAnim.speed = 1f
                 itemRv.likeAnim.playAnimation()
             }else{
                 itemRv.likeAnim.speed = -1f
                 itemRv.likeAnim.playAnimation()
             }
             itemRv.likeBtn.setOnClickListener {
                 if (index != -1){
                     val l = MySharedPreference.adibList
                     MySharedPreference.adibList = l
                 }else{
                     val l = MySharedPreference.adibList
                     l.add(user)
                     MySharedPreference.adibList = l
                 }
                 if (save == 1){
                     list.remove(user)
                     notifyItemRemoved(position)
                     notifyItemRangeRemoved(position, mList.size)
                 }else{
                     notifyItemChanged(position)
                 }
             }*/
        }
    }

    interface MyClick {
        fun click(user: User)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size

}