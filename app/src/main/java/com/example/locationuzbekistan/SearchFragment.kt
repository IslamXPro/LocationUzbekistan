package com.example.locationuzbekistan

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import com.example.locationuzbekistan.adapters.RvAdapter
import com.example.locationuzbekistan.databinding.FragmentSearchBinding
import com.example.locationuzbekistan.models.User
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import kotlin.collections.ArrayList


class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var fireBaseStore: FirebaseFirestore
    private lateinit var list: ArrayList<User>
    private lateinit var list2: ArrayList<User>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(layoutInflater)

        binding.searchBtn.setQuery("", true)
        binding.searchBtn.isFocusable = true
        binding.searchBtn.isIconified = false
        binding.searchBtn.requestFocusFromTouch()

        onResume()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        fireBaseStore = FirebaseFirestore.getInstance()

        fireBaseStore.collection("user")
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    list = ArrayList()
                    val result = it.result
                    result?.forEach { queryDocumentSnapshot ->
                        val user = queryDocumentSnapshot.toObject(User::class.java)
                        list.add(user)
                    }
                }
                binding.rvSearch.adapter = RvAdapter(binding.root.context, list, object : RvAdapter.MyClick {
                    override fun click(user: User) {

                    }
                })
            }
        list2 = ArrayList()
        binding.searchBtn.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onQueryTextChange(newText: String): Boolean {
                /* var list = ArrayList<User>()
                 for (user in InternetData.list) {
                     for (i in 0 until user.placeName?.length!!){
                         if (user.placeName?.subSequence(0, i).toString()
                                 .lowercase(Locale.getDefault()) == p0?.toLowerCase()){
                             list.add(user)
                         }
                     }
                 }
                 rvAdapter = RvAdapter(list, object : RvAdapter.MyClick{
                     override fun click(user: User) {

                     }
                 })
                 binding.rvSearch.adapter= rvAdapter*/
                list2.clear()
                val text = newText.lowercase(Locale.getDefault())
                if (text.isNotEmpty()) {
                    list.forEach {
                        if (it.placeName?.lowercase(Locale.getDefault())!!.contains(text)) {
                            list2.add(it)
                        }
                    }
                    binding.rvSearch.adapter = RvAdapter(binding.root.context, list2, object : RvAdapter.MyClick {
                        override fun click(user: User) {
                        }
                    })
                } else {
                    list2.clear()
                    list2.addAll(list)
                    binding.rvSearch.adapter = RvAdapter(binding.root.context, list, object : RvAdapter.MyClick {
                        override fun click(user: User) {
                        }
                    })
                }
                return true
            }
        })

    }

}