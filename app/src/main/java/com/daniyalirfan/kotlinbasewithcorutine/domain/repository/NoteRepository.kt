package com.daniyalirfan.kotlinbasewithcorutine.domain.repository
import com.daniyalirfan.kotlinbasewithcorutine.data.models.PostsResponse
import retrofit2.Response

interface NoteRepository {
    suspend fun getPosts() : Response<PostsResponse>
}