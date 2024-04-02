package com.example.databindingtask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController

import com.example.databindingtask.databinding.FragmentCreateUserBinding

class CreateUserFragment : Fragment(R.layout.fragment_create_user) {
    private lateinit var binding: FragmentCreateUserBinding
    private val viewModel: CreateUserViewModel by viewModels {
        CreateUserViewFactory(
            requireActivity().application,
            RegisterRepository(
                RegisterDatabase.getDatabase(requireContext().applicationContext)
                    .registerDatabaseDao()
            )
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateUserBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ButtonCreateUser.setOnClickListener {
            val Username = binding.EdittextUserid.text.toString()
            val Password = binding.EdittextPassword.text.toString()

            if (validateInputs(Username, Password)) {
                viewModel.saveUserData(Username, Password)

                binding.EdittextUserid.text.clear()
                binding.EdittextPassword.text.clear()
                findNavController().navigate(R.id.action_createUserFragment_to_userListFragment)

            }

        }

    }

    private fun validateInputs(username: String, password: String): Boolean {
        if (username.isEmpty()) {
            binding.EdittextUserid.error = "Please enter a username"
            return false
        }

        if (password.isEmpty()) {
            binding.EdittextPassword.error = "Please enter a password"
            return false
        }
        return true
    }
}