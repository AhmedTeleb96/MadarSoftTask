package com.ahmedteleb.madarsofttask.app.home.addUser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ahmedteleb.madarsofttask.databinding.FragmentAddUserBinding
import com.ahmedteleb.madarsofttask.domain.entity.Gender
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddUserFragment : Fragment() {

    private val viewModel: AddUserViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentAddUserBinding.inflate(inflater, container, false)

        binding.btnAddUser.setOnClickListener {

            val name = binding.edFullName.text.toString()
            val age = binding.edAge.text.toString()
            val job = binding.edJobTitle.text.toString()
            val gender = when (binding.chipGroupGender.checkedChipId) {
                binding.chipMale.id -> Gender.Male
                binding.chipFemale.id -> Gender.Female
                else -> null
            }

            viewModel.addUser(name, age, job, gender)
        }

        viewModel.error.observe(viewLifecycleOwner) {
            it?.let { error ->
                Toast.makeText(activity, error, Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.loading.observe(viewLifecycleOwner) {
            binding.btnAddUser.isEnabled = !it
        }
        viewModel.success.observe(viewLifecycleOwner){
            if (it)
            {
                Toast.makeText(activity, "User added successfully", Toast.LENGTH_SHORT).show()
                binding.edFullName.setText("")
                binding.edAge.setText("")
                binding.edJobTitle.setText("")
                binding.chipGroupGender.clearCheck()
                viewModel.onSuccessDone()
            }
        }
        return binding.root
    }


}