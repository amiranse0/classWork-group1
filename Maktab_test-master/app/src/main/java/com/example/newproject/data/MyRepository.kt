package com.example.newproject.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newproject.data.source.UserDataSource
import com.example.newproject.model.UserFromServer

class MyRepository(private val dataSource: UserDataSource<List<UserFromServer>>) {
    fun getUsersFromServer():LiveData<List<UserFromServer>>{
        val usersLiveData = MutableLiveData<List<UserFromServer>>()
        dataSource.getUsersFromServer(object : MyCallback<List<UserFromServer>>{
            override fun onResponse(response: List<UserFromServer>) {
                usersLiveData.postValue(response)
            }

            override fun onFailure(throwable: Throwable) {
                Log.d("TAG", throwable.message.toString())
            }
        })
        return usersLiveData
    }
}