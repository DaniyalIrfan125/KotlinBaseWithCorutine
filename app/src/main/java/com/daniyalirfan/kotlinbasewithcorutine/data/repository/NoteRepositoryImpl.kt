package com.daniyalirfan.kotlinbasewithcorutine.data.repository

import com.daniyalirfan.kotlinbasewithcorutine.data.local.db.AppDao
import com.daniyalirfan.kotlinbasewithcorutine.data.models.PostsResponse
import com.daniyalirfan.kotlinbasewithcorutine.data.remote.ApiService
import com.daniyalirfan.kotlinbasewithcorutine.domain.repository.NoteRepository
import retrofit2.Response
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
        private val apiService: ApiService,
        localDataSource: AppDao
) : NoteRepository {
    override suspend fun getPosts(): Response<PostsResponse> {
        return apiService.getPosts()
    }

}