package com.example.locationuzbekistan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.locationuzbekistan.Utils.InternetData
import com.example.locationuzbekistan.adapters.RvAdapter
import com.example.locationuzbekistan.databinding.FragmentHomeBinding
import com.example.locationuzbekistan.models.User
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var fireBaseStore: FirebaseFirestore
    lateinit var list: ArrayList<User>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)

        onResume()

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        fireBaseStore = FirebaseFirestore.getInstance()

        fireBaseStore.collection("user")
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful){
                    list = ArrayList()
                    val result = it.result
                    result?.forEach { queryDocumentSnapshot ->
                        val user = queryDocumentSnapshot.toObject(User::class.java)
                            list.add(user)
                    }
                }
                binding.recycle.adapter = RvAdapter(binding.root.context, list,object : RvAdapter.MyClick{
                    override fun click(user: User) {

                    }
                })
            }
    }

}