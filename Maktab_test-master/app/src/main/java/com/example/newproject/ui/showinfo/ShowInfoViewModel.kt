package com.example.newproject.ui.showinfo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newproject.data.network.NetworkManager
import com.example.newproject.model.UserDetails
import com.example.newproject.model.UserFromServer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.nio.channels.NetworkChannel

class ShowInfoViewModel:ViewModel() {

    private val _user = MutableLiveData<UserDetails>()
    val user: LiveData<UserDetails> = _user


    fun showDetails(id:String){
            NetworkManager.service.getShowInfo(id).enqueue(object : Callback<UserDetails?> {
                override fun onResponse(
                    call: Call<UserDetails?>,
                    response: Response<UserDetails?>
                ) {
                    _user.postValue(response.body())
                    Log.d("response",response.body()!!._id.toString())
                }

                override fun onFailure(call: Call<UserDetails?>, t: Throwable) {
                    Log.d("showDetails",t.message.toString())
                }
            })
    }



}