package com.example.newproject.ui.signup

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newproject.data.network.NetworkManager.service
import com.example.newproject.model.User
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response

class ViewModelHome : ViewModel() {
    val failed by lazy {
        MutableLiveData(false)
    }

    private val _userId = MutableLiveData<String>()
    val userId: LiveData<String> = _userId

    fun createUser(user: User) {
        val call = service.createUser(user)
        call.enqueue(object : retrofit2.Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                val id = response.body() ?: "-1"
                _userId.value = id
                Log.d("response_id", id)
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                failed.postValue(true)
                Log.d("response_id", t.message.toString())
            }
        })
    }
}