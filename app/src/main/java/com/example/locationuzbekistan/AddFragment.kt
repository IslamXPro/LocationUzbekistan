package com.example.locationuzbekistan

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.locationuzbekistan.adapters.LastADapter
import com.example.locationuzbekistan.databinding.FragmentAddBinding
import com.example.locationuzbekistan.models.ImageClass
import com.example.locationuzbekistan.models.User
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.DialogOnDeniedPermissionListener
import com.karumi.dexter.listener.single.PermissionListener
import java.io.File
import java.util.*
import kotlin.collections.ArrayList
@SuppressLint("NotifyDataSetChanged","Range")
class AddFragment : Fragment() {

    private val RESULT_IMAGE = 10
    private lateinit var binding: FragmentAddBinding
    private lateinit var firebaseFirestore: FirebaseFirestore
    private lateinit var firebaseStorage: FirebaseStorage
    private var mStorageReference: StorageReference? = null
    private var fileNameList: ArrayList<String>? = null
    private lateinit var list: ArrayList<String>
//    private var fileDoneList: ArrayList<String>? = null
    private var mAdapter: LastADapter? = null
//    private lateinit var list: ArrayList<ImageClass>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAddBinding.inflate(layoutInflater)

        fileNameList = ArrayList()
        list = ArrayList()
  //      fileDoneList = ArrayList()
   //     list = ArrayList()

        firebaseFirestore = FirebaseFirestore.getInstance()
        firebaseStorage = FirebaseStorage.getInstance()
        mStorageReference = FirebaseStorage.getInstance().reference

        mAdapter = LastADapter(binding.root.context, fileNameList as ArrayList<String>)
        binding.imgRecycle.adapter = mAdapter
        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
        Toast.makeText(binding.root.context,  "", Toast.LENGTH_SHORT).show()


        binding.adImg.setOnClickListener {
            requestPermission()
            val intent = Intent()
            intent.type = "image/*"
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                Intent.createChooser(intent, "Select Picture"),
                RESULT_IMAGE
            )
        }
        binding.adAddBtn.setOnClickListener {
            val name = binding.adName.text.toString().trim()
            val description = binding.adDecrp.text.toString().trim()
            val link = binding.adLink.text.toString().trim()


            if(name.isNotEmpty() && description.isNotEmpty() && link.isNotEmpty()){
                val user = User("",name,description,link,fileNameList)
                firebaseFirestore.collection("user")
                    .add(user)
                    .addOnSuccessListener {
                        Toast.makeText(binding.root.context, "Done", Toast.LENGTH_SHORT).show()
                        binding.adName.text.clear()
                        binding.adDecrp.text.clear()
                        binding.adLink.text.clear()
                        fileNameList = ArrayList()
                    //    fileDoneList = ArrayList()
                        mAdapter = LastADapter(binding.root.context,fileNameList!!)
                        binding.imgRecycle.adapter = mAdapter
                        mAdapter?.notifyDataSetChanged()
                    }
                    .addOnFailureListener{
                        Toast.makeText(binding.root.context, "Fail", Toast.LENGTH_SHORT).show()
                    }
            }
        }

        return  binding.root
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RESULT_IMAGE && resultCode == Activity.RESULT_OK) {
            if (data!!.clipData != null) {
                //Toast.makeText(this, "Selected Multiple Files", Toast.LENGTH_SHORT).show();
                val totalItems = data.clipData!!.itemCount
                for (i in 0 until totalItems) {
                    val fileUri = data.clipData!!.getItemAt(i).uri
                    val fileName = getFileName(fileUri)
                    fileNameList?.add(fileName)
                   // fileDoneList?.add("Uploading")
                    mAdapter = LastADapter(binding.root.context,fileNameList!!)
                    binding.imgRecycle.adapter = mAdapter
                    mAdapter?.notifyDataSetChanged()
                    //list.addAll(listOf(ImageClass(fileNameList)))
               //     mAdapter!!.notifyDataSetChanged()
                    val fileToUpload = mStorageReference!!.child("images").child(fileName)
                    fileToUpload.putFile(fileUri)
                        .addOnSuccessListener { //Toast.makeText(binding.root.context.this, "Done", Toast.LENGTH_SHORT).show();
                   //          fileDoneList?.removeAt(i)
                 //            fileDoneList?.add(i, "Done")
                     //       mAdapter?.notifyDataSetChanged()
                            //finish upload image
                            binding.adImg.visibility = View.GONE
                            Toast.makeText(binding.root.context, "Finish", Toast.LENGTH_SHORT).show()
                      //      list.addAll(listOf(ImageClass(fileNameList)))
/*                            mStorageReference = FirebaseStorage.getInstance().reference.child("Tashkent")
                            try {
                                val localFile = File.createTempFile("fd9a2553-6dc7-4ae2-a553-bd656296772e.jpeg", "jpeg")
                                mStorageReference!!.getFile(fileUri)
                                    .addOnSuccessListener { //success
                                        val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
                                        val user = ImageClass()
                                        user.bitmap = bitmap
                                        binding.bitImg.setImageBitmap(user.bitmap)
                                    }.addOnFailureListener {
                                        //fail
                                    }
                            } catch (e: IOException) {
                                e.printStackTrace()
                            }*/
                           /* mStorageReference = FirebaseStorage.getInstance().reference.child("images")
                            mStorageReference?.listAll()?.addOnSuccessListener(OnSuccessListener<ListResult> { listResult ->
                                for (file in listResult.items) {
                                    file.downloadUrl.addOnSuccessListener { uri -> // adding the url in the arraylist
                                        fileNameList?.add(uri.toString())
                                        Log.e("Itemvalue", uri.toString())
                                    }.addOnSuccessListener {
                                        binding.imgRecycle.adapter = mAdapter
                                        mAdapter?.notifyDataSetChanged()
                                        //  progressBar.setVisibility(View.GONE)
                                    }
                                }
                            })*/
                        }

                }
            } else if (data.data != null) {
                Toast.makeText(binding.root.context, "Selected Single File", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun getFileName(uri: Uri): String {
        var result: String? = null
        if (uri.scheme == "content") {
            val cursor: Cursor = requireActivity().contentResolver.query(uri, null, null, null, null)!!
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

    private fun showSettingsDialog() {
        val builder = AlertDialog.Builder(binding.root.context)
        builder.setTitle("Need Permissions")
        builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.")
        builder.setPositiveButton(
            "GO TO SETTINGS"
        ) { dialog, which ->
            dialog.cancel()
            openSettings()
        }
        builder.setNegativeButton(
            "Cancel"
        ) { dialog, which -> dialog.cancel() }
        builder.show()
    }

    private fun openSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", requireActivity().packageName, null)
        intent.data = uri
        startActivityForResult(intent, 101)
    }

    private fun requestPermission() {
        Dexter.withContext(binding.root.context)
            .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(permissionGrantedResponse: PermissionGrantedResponse?) {}
                override fun onPermissionDenied(permissionDeniedResponse: PermissionDeniedResponse?) {
                    DialogOnDeniedPermissionListener.Builder
                        .withContext(binding.root.context)
                        .withTitle("Read External Storage permission")
                        .withMessage("Read External Storage  permission is needed")
                        .withButtonText(android.R.string.ok)
                        .withIcon(R.mipmap.ic_launcher)
                        .build()


//                        Intent permIntent = new Intent();
//                        permIntent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//                        Uri uri = Uri.fromParts("package",getPackageName(),null);
//                        permIntent.setData(uri);
//                        startActivity(permIntent);
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissionRequest: PermissionRequest?,
                    permissionToken: PermissionToken
                ) {
                    permissionToken.continuePermissionRequest()
                }
            }).check()
    }
}