package com.daniyalirfan.kotlinbasewithcorutine.domain.repository

import com.daniyalirfan.kotlinbasewithcorutine.data.models.PostsResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface MainRepository {

    fun getPosts(): Flow<Response<PostsResponse>>

}