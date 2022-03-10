package com.example.newproject.ui.users

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newproject.data.MyRepository
import com.example.newproject.data.network.NetworkManager
import com.example.newproject.model.UserFromServer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel(private val repository: MyRepository):ViewModel() {

    var _listUsers = MutableLiveData<List<UserFromServer>>()

private val _searchResult = MutableLiveData<List<UserFromServer>>()
    val searchResult: LiveData<List<UserFromServer>> = _searchResult

    fun getUsersFromServer():LiveData<List<UserFromServer>>{
        return repository.getUsersFromServer()
    }

    fun searchFromUsers(query:HashMap<String,String>){
        NetworkManager.service.getUsers(query).enqueue(object : Callback<List<UserFromServer>?> {
            override fun onResponse(
                call: Call<List<UserFromServer>?>,
                response: Response<List<UserFromServer>?>
            ) {
                _searchResult.postValue(response.body())
            }

            override fun onFailure(call: Call<List<UserFromServer>?>, t: Throwable) {
                Log.d("TAG","searchResult:" + t.message.toString())
            }
        })

    }
    fun getUserFromFirstName(firstname:String){
        if (firstname.isNotBlank()){
            searchFromUsers( hashMapOf("firstName" to firstname))
        }


    }
}