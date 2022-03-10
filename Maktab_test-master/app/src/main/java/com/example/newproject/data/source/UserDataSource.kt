package com.example.newproject.data.source

import com.example.newproject.data.MyCallback

interface UserDataSource<T> {
    fun getUsersFromServer(callback: MyCallback<T>)
}