package com.example.newproject.data.source

import com.example.newproject.data.MyCallback
import com.example.newproject.data.network.NetworkManager
import com.example.newproject.model.UserFromServer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object RemoteUserDataSource: UserDataSource<List<UserFromServer>> {
    override fun getUsersFromServer(callback: MyCallback<List<UserFromServer>>) {
        NetworkManager.service.getUsers().enqueue(object : Callback<List<UserFromServer>?> {
            override fun onResponse(
                call: Call<List<UserFromServer>?>,
                response: Response<List<UserFromServer>?>
            ) {
                val temp = response.body()
                if (temp != null) callback.onResponse(temp)
            }

            override fun onFailure(call: Call<List<UserFromServer>?>, t: Throwable) {
                callback.onFailure(t)
            }
        })
    }

}