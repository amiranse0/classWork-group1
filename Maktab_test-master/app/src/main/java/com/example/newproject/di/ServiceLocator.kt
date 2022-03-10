package com.example.newproject.di

import com.example.newproject.data.MyRepository
import com.example.newproject.data.source.RemoteUserDataSource

object ServiceLocator {
    val userRepo = MyRepository(RemoteUserDataSource)
}