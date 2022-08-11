package com.ahmedteleb.madarsofttask.app.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ahmedteleb.madarsofttask.R
import com.ahmedteleb.madarsofttask.app.home.addUser.AddUserFragment
import com.ahmedteleb.madarsofttask.app.home.viewUsers.ViewUsersFragment
import com.ahmedteleb.madarsofttask.databinding.FragmentHomeBinding
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHomeBinding.inflate(inflater)


        val pagerAdapter = FragmentPagerItemAdapter(
            childFragmentManager,
            FragmentPagerItems.with(context)
                .add(getString(R.string.add_user), AddUserFragment::class.java)
                .add(getString(R.string.view_users), ViewUsersFragment::class.java)
                .create()
        )

        binding.viewPager.apply {
            offscreenPageLimit = 0
            adapter = pagerAdapter
            currentItem = 0
        }
        binding.tabLayout.setupWithViewPager(binding.viewPager)


        return binding.root
    }

}