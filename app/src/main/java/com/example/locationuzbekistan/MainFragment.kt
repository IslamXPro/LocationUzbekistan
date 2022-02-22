package com.example.locationuzbekistan

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.example.locationuzbekistan.adapters.ViewPagerAdapter
import com.example.locationuzbekistan.databinding.ActivityMainBinding
import com.example.locationuzbekistan.databinding.FragmentMainBinding
import com.example.passregistr.Utils.Back.isHome

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    @SuppressLint("ResourceAsColor", "ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(layoutInflater)
        isHome = true
        binding.viewpager.adapter = ViewPagerAdapter(parentFragmentManager)
        //Scrolln qilishni o'chirish
        binding.viewpager.setOnTouchListener(View.OnTouchListener { arg0, arg1 -> true })
        binding.homeText.setTextColor(Color.parseColor("#000000"))
        binding.homeIc.setImageResource(R.drawable.home_ic_click)
        binding.viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        isClick(false)
                        binding.homeIc.setImageResource(R.drawable.search_ic_click)
                        binding.homeText.setTextColor(Color.parseColor("#000000"))
                    }
                    1 -> {
                        isClick(false)
                        binding.searchIc.setImageResource(R.drawable.search_ic_click)
                        binding.searchText.setTextColor(Color.parseColor("#000000"))
                    }
                    2 -> {
                        isClick(false)
                        binding.addIc.setImageResource(R.drawable.add_ic_click)
                        binding.addText.setTextColor(Color.parseColor("#000000"))
                    }
                    3 -> {
                        isClick(false)
                        binding.saveIc.setImageResource(R.drawable.save_ic_click)
                        binding.saveText.setTextColor(Color.parseColor("#000000"))
                    }
                    4 -> {
                        isClick(false)
                        binding.profileIc.setImageResource(R.drawable.profile_ic_click)
                        binding.profileText.setTextColor(Color.parseColor("#000000"))
                    }
                }
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

        binding.homeBtn.setOnClickListener {
            binding.viewpager.currentItem = 0
            isClick(false)
            binding.homeIc.setImageResource(R.drawable.home_ic_click)
            binding.homeText.setTextColor(Color.parseColor("#000000"))

        }
        binding.searchBtn.setOnClickListener {
            binding.viewpager.currentItem = 1
            isClick(false)
            binding.searchIc.setImageResource(R.drawable.search_ic_click)
            binding.searchText.setTextColor(Color.parseColor("#000000"))
        }
        binding.addBtn.setOnClickListener {
            binding.viewpager.currentItem = 2
            isClick(false)
            binding.addIc.setImageResource(R.drawable.add_ic_click)
            binding.addText.setTextColor(Color.parseColor("#000000"))
        }
        binding.saveBtn.setOnClickListener {
            binding.viewpager.currentItem = 3
            isClick(false)
            binding.saveIc.setImageResource(R.drawable.save_ic_click)
            binding.saveText.setTextColor(Color.parseColor("#000000"))

        }
        binding.profileBtn.setOnClickListener {
            binding.viewpager.currentItem = 4
            isClick(false)
            binding.profileIc.setImageResource(R.drawable.profile_ic_click)
            binding.profileText.setTextColor(Color.parseColor("#000000"))
        }



        return binding.root
    }

    @SuppressLint("ResourceAsColor")
    fun isClick(off: Boolean) {
        if (off) {
        } else {
            binding.homeIc.setImageResource(R.drawable.home_ic)
            binding.homeText.setTextColor(R.color.default_t_color)
            binding.searchIc.setImageResource(R.drawable.serach_ic)
            binding.searchText.setTextColor(R.color.default_t_color)
            binding.addIc.setImageResource(R.drawable.add_ic)
            binding.addText.setTextColor(R.color.default_t_color)
            binding.saveIc.setImageResource(R.drawable.save_ic)
            binding.saveText.setTextColor(R.color.default_t_color)
            binding.profileIc.setImageResource(R.drawable.profile_ic)
            binding.profileText.setTextColor(R.color.default_t_color)
        }
    }
}