package com.example.newproject.ui.signup


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController


import com.example.newproject.R
import com.example.newproject.databinding.FragmentHomeBinding
import com.example.newproject.model.User

class FragmentHome : Fragment(R.layout.fragment_home) {
    val list = listOf("Movie", "Game", "Sport")
    val viewModelHome: ViewModelHome by viewModels()
    lateinit var binding: FragmentHomeBinding
    val navController by lazy {
        findNavController()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        init()
        initObserve()
        goToListUser()
    }

    private fun goToListUser() {
        binding.getListUsers.setOnClickListener {
            navController.navigate(R.id.action_fragmentHome_to_userFragment)
        }
    }

    private fun init() {
        with(binding) {
            homeBtn.setOnClickListener {
                val user = User(
                    homeName.text.toString(),
                    listOf("Movie", "Sport"),
                    homeFamily.text.toString(),
                    homeNationalCode.text.toString()
                )
                viewModelHome.createUser(user)

            }
        }
    }

    fun initObserve(){
        viewModelHome.userId.observe(viewLifecycleOwner, Observer {
            navController.navigate(FragmentHomeDirections.actionFragmentHomeToUploadImage(it))
        })
    }
}
