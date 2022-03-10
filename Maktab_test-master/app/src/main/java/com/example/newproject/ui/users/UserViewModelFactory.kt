package com.example.newproject.ui.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newproject.di.ServiceLocator
import java.lang.IllegalArgumentException

class UserViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            val userViewModel = UserViewModel(ServiceLocator.userRepo)
            return userViewModel as T
        }
        throw IllegalArgumentException("unknown type")
    }
}