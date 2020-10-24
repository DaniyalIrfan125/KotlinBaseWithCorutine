package com.daniyalirfan.kotlinbasewithcorutine.data.remote.reporitory

import com.daniyalirfan.kotlinbasewithcorutine.data.local.db.AppDao
import com.daniyalirfan.kotlinbasewithcorutine.data.remote.ApiService
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService,
    localDataSource: AppDao
) {

    suspend fun getPosts() = apiService.getPosts()

}