package com.ahmedteleb.madarsofttask.app.home.viewUsers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ahmedteleb.madarsofttask.databinding.FragmentViewUsersBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewUsersFragment : Fragment() {

    private val viewModel: ViewUsersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentViewUsersBinding.inflate(inflater, container, false)

        val usersAdapter = UsersAdapter()
        binding.recyclerViewViewUsers.adapter = usersAdapter

        viewModel.users.observe(viewLifecycleOwner){
            if(it!=null)
            {
                usersAdapter.submitList(it)
            }
        }
        viewModel.loading.observe(viewLifecycleOwner){
            if(it)
            {
                Toast.makeText(activity, "loading ...", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.error.observe(viewLifecycleOwner){
            if(it!=null)
            {
                Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }

}