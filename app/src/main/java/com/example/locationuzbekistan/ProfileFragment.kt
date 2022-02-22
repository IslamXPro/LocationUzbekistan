package com.example.locationuzbekistan

import android.annotation.SuppressLint
import android.database.Cursor
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.locationuzbekistan.adapters.GetImageAdapter
import com.example.locationuzbekistan.databinding.FragmentProfileBinding
import com.example.locationuzbekistan.models.ModelClass
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ListResult
import com.google.firebase.storage.StorageReference
import java.io.File
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList


class ProfileFragment : Fragment() {

    private val RESULT_IMAGE = 10
    lateinit var binding: FragmentProfileBinding
    private var mStorageReference: StorageReference? = null
    lateinit var getImageAdapter: GetImageAdapter
    private lateinit var list: ArrayList<String>

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater)

        list = ArrayList()
        getImageAdapter = GetImageAdapter(binding.root.context,list)
        binding.getrecycle.setHasFixedSize(true)
        binding.getrecycle.adapter = getImageAdapter
 //       base = FirebaseDatabase.getInstance().getReference("user")
 /*       base.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (userSnapshot in snapshot.children){
                        val user = userSnapshot.getValue(//class)
                            //list.add(user)
                    }
                    //madapter.adapter = MyAdapter(list)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })*/
        mStorageReference = FirebaseStorage.getInstance().reference.child("user")
        mStorageReference?.listAll()?.addOnSuccessListener(OnSuccessListener<ListResult> { listResult ->
            for (file in listResult.items) {
                file.downloadUrl.addOnSuccessListener { uri -> // adding the url in the arraylist
                    list.add(uri.toString())
                    Log.e("Itemvalue", uri.toString())
                }.addOnSuccessListener {
                    binding.getrecycle.adapter = getImageAdapter
                  //  progressBar.setVisibility(View.GONE)
                }
            }
        })



        /*try {
            val localFile = File.createTempFile("fd9a2553-6dc7-4ae2-a553-bd656296772e.jpeg", "jpeg")
            mStorageReference!!.getFile(localFile)
                .addOnSuccessListener { //success
                    val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
                    binding.imgGet.setImageBitmap(bitmap)
                }.addOnFailureListener {
                    //fail
                }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        val localFile = File.createTempFile("img", "jpeg")
        mStorageReference!!.child("Tashkent")
            .getFile(localFile)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    imglist = ArrayList()
                    val result = it.result

                }
            }*/


        //     mStorageReference!!.child("Tashkent")

/*        mStorageReference!!.child("folderA/1.png").getDownloadUrl().addOnSuccessListener(new
                OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.load(uri)
                // Got the download URL for 'users/me/profile.png'
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });*/


/*        val islandRef = mStorageReference!!.child("Tashkent/fd9a2553-6dc7-4ae2-a553-bd656296772e.jpeg")

        val ONE_MEGABYTE: Long = 1024 * 1024
        islandRef.getBytes(ONE_MEGABYTE).addOnSuccessListener {
            // Data for "images/island.jpg" is returned, use this as needed
            Log.d(TAG, "onCreateView: $it")
            Glide.with(binding.root.context)
                .load(islandRef)
                .into(binding.imgGet)
        }.addOnFailureListener {
            // Handle any errors
        }*/

/*        val islandRef = mStorageReference!!.child("Tashkent/fd9a2553-6dc7-4ae2-a553-bd656296772e.jpeg")

        val localFile = File.createTempFile("Tashkent", "jpeg")

        islandRef.getFile(localFile).addOnSuccessListener {
            // Local temp file has been created
            Toast.makeText(binding.root.context, "$it", Toast.LENGTH_SHORT).show()
            Glide.with(binding.root.context)
                .load(islandRef)
                .into(binding.imgGet)
        }.addOnFailureListener {
            // Handle any errors
        }*/

/*        // Reference to an image file in Cloud Storage
        val storageReference = Firebase.storage.reference

// ImageView in your Activity
        val imageView = binding.imgGet

// Download directly from StorageReference using Glide
// (See MyAppGlideModule for Loader registration)
        Glide.with(binding.root.context)
            .load(mStorageReference)
            .into(imageView)*/


/*        val imageView1 = binding.imgGet
        val storageref = Firebase.storage.reference
        val path = mStorageReference!!.child("Tashkent")
        val LoadImage = "fd9a2553-6dc7-4ae2-a553-bd656296772e.jpeg"
        val pathReference = path.child(LoadImage)
        Toast.makeText(binding.root.context, "$pathReference", Toast.LENGTH_SHORT).show()
      //  Picasso.get().load(Uri.parse(pathReference.toString())).into(binding.imgGet)
        Glide.with(binding.root.context)
            .load(Uri.parse(pathReference.toString()))
            .into(binding.imgGet)*/
/*        val activeDownloadTasks = mStorageReference!!.child("Tashkent").activeDownloadTasks
        activeDownloadTasks.get(1)*/
        /*    val list = mStorageReference!!.child("Tashkent").list(RESULT_IMAGE)
            list.addOnCompleteListener {
                val result = list.result
                imglist.add(ModelClass(result.toString()))
            }*/
//        child.getFile(imgUri!!)
//            .addOnSuccessListener {
//                imglist.addAll(listOf(ModelClass(getFileName(imgUri!!))))
//                getImageAdapter.notifyDataSetChanged()
//            }

        /*imglist.add(ModelClass(getFileName(imgUri!!)))
        Toast.makeText(binding.root.context, "Add ModelClass", Toast.LENGTH_SHORT).show()
        getImageAdapter.notifyDataSetChanged()
        val child = mStorageReference!!.child("Tashkent")
        child.getFile(imgUri!!)
            .addOnSuccessListener { //Toast.makeText(binding.root.context.this, "Done", Toast.LENGTH_SHORT).show();

                imglist.add(ModelClass(getFileName(imgUri!!)))
                getImageAdapter.notifyDataSetChanged()
                Toast.makeText(binding.root.context, "Add Uri", Toast.LENGTH_SHORT).show()
            }*/

        return binding.root
    }

/*    @SuppressLint("NotifyDataSetChanged")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RESULT_IMAGE && resultCode == Activity.RESULT_OK) {
            if (data!!.clipData != null) {
                //Toast.makeText(this, "Selected Multiple Files", Toast.LENGTH_SHORT).show();
                val totalItems = data.clipData!!.itemCount
                for (i in 0 until totalItems) {
                    val fileUri = data.clipData!!.getItemAt(i).uri
                    //val fileName = getFileName(fileUri)


                }
            } else if (data.data != null) {
                Toast.makeText(binding.root.context, "Selected Single File", Toast.LENGTH_SHORT).show()
            }
        }
    }*/

    @SuppressLint("Range")
    fun getFileName(uri: Uri): String {
        var result: String? = null
        if (uri.scheme == "content") {
            val cursor: Cursor =
                requireActivity().contentResolver.query(uri, null, null, null, null)!!
            try {
                if (cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                }
            } finally {
                cursor.close()
            }
        }
        if (result == null) {
            result = uri.path
            val cut = result!!.lastIndexOf('/')
            if (cut != -1) {
                result = result.substring(cut + 1)
            }
        }
        return result
    }


}