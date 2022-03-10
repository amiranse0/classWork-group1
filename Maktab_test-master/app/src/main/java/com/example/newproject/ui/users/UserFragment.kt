package com.example.newproject.ui.users

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newproject.R
import com.example.newproject.databinding.UserFragmentBinding
import com.example.newproject.model.User
import com.example.newproject.model.UserFromServer

class UserFragment : Fragment(R.layout.user_fragment) {

    lateinit var binding: UserFragmentBinding

    private val viewModel: UserViewModel by activityViewModels({
        UserViewModelFactory()
    })

    private var listUsers = mutableListOf<UserFromServer>()
    val navController by lazy { findNavController() }


    private lateinit var recyclerAdaptor: RecyclerAdaptor

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = UserFragmentBinding.bind(view)
        var recyclerView = binding.rc

        viewModel.getUsersFromServer()

        viewModel.getUsersFromServer().observe(viewLifecycleOwner) {
            listUsers.clear()
            listUsers.addAll(it)
            recyclerAdaptor.notifyDataSetChanged()
        }
        recyclerAdaptor = RecyclerAdaptor(listUsers)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = recyclerAdaptor
         viewModel.searchResult.observe(viewLifecycleOwner, Observer {
             listUsers.clear()
             listUsers.addAll(it)
             recyclerAdaptor.notifyDataSetChanged()

         })
        binding.btsearch.setOnClickListener {

          viewModel.getUserFromFirstName(binding.edsearch.text.toString())

        }

        recyclerAdaptor = RecyclerAdaptor(listUsers)
        recyclerAdaptor.setItemUserClick(object :RecyclerAdaptor.ItemClick{
            override fun viewClick(position: Int, v: View?) {
                Log.d("click" , "Position ${position}")
                Log.d("click" , "Position-Id ${listUsers[position]._id}")
                navController.navigate(UserFragmentDirections.actionUserFragmentToShowInfo(listUsers[position]._id))
            }
        } )
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = recyclerAdaptor




    }

}