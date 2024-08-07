package com.daniyalirfan.kotlinbasewithcorutine.data.remote.repository

import com.daniyalirfan.kotlinbasewithcorutine.data.local.db.AppDao
import com.daniyalirfan.kotlinbasewithcorutine.data.models.PostsResponse
import com.daniyalirfan.kotlinbasewithcorutine.data.remote.ApiService
import com.daniyalirfan.kotlinbasewithcorutine.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    appDao: AppDao
) : MainRepository {

    override fun getPosts(): Flow<Response<PostsResponse>> {
        return flow { emit(apiService.getPosts()) }
    }


}