package com.example.newproject.data.network

import com.example.newproject.model.User
import com.example.newproject.model.UserDetails
import com.example.newproject.model.UserFromServer
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface MyService {

    @POST("users")
    fun createUser(
        @Body user: User
    ) : Call<String>

    @Multipart
    @POST("users/{id}/image")
    fun uploadImage(@Path("id")id:String,
    @Part image : MultipartBody.Part): Call<Any>

    @GET("http://papp.ir/api/v1/users")
    fun getUsers(@QueryMap users:HashMap<String,String> = hashMapOf()):Call<List<UserFromServer>>

    @GET("http://papp.ir/api/v1/users/{id}")
    fun getShowInfo(@Path("id")id:String):Call<UserDetails>
}