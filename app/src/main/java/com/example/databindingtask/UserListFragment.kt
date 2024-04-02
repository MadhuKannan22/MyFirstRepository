package com.example.databindingtask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.databindingtask.databinding.CardViewDesignBinding
import com.example.databindingtask.databinding.FragmentUserListBinding
import kotlinx.coroutines.launch

class UserListFragment : Fragment(R.layout.fragment_user_list) {
    lateinit var adapter: MyViewHolder
    private lateinit var binding: FragmentUserListBinding
    //private lateinit var registerEntity: RegisterEntity

    private val userListViewModel: UserListViewModel by viewModels {
        UserListViewFactory(
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
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_list,container,false)
        setupRecyclerView(view)
        return view
    }

    private fun setupRecyclerView(view: View) {
        //val registerEntity = RegisterEntity(0, "Id", "Password")
        binding = FragmentUserListBinding.bind(view)
        adapter = MyViewHolder{registerEntity ->
            userListViewModel.deleteUser(registerEntity)}
        binding.designRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        binding.designRecyclerview.adapter = adapter


        lifecycleScope.launch {
            userListViewModel.userList.collect { userList: List<RegisterEntity> ->
                adapter.submitList(userList)
            }
        }



    }



}


