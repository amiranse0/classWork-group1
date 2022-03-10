package com.example.newproject.data

interface MyCallback<T> {
    fun onResponse(response:T)
    fun onFailure(throwable: Throwable)
}