package com.daniyalirfan.kotlinbasewithcorutine.domain.usecases

import com.daniyalirfan.kotlinbasewithcorutine.data.models.PostsResponse
import com.daniyalirfan.kotlinbasewithcorutine.utils.Resource
import com.daniyalirfan.kotlinbasewithcorutine.data.repository.NoteRepositoryImpl
import com.daniyalirfan.kotlinbasewithcorutine.utils.NetworkHelper
import javax.inject.Inject


class NoteUseCase @Inject constructor(
        private val repository: NoteRepositoryImpl,
        private val networkHelper: NetworkHelper
) {
    suspend fun fetchPostsFromApi(): Resource<PostsResponse> {
        if (networkHelper.isNetworkConnected()) {
            repository.getPosts().let {
                if (it.isSuccessful) {
                    return Resource.success(it.body()!!)
                } else {
                    return Resource.error(it.message(), null)
                }
            }
        } else return Resource.error("No internet connection", null)
    }
}
