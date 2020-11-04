package com.daniyalirfan.kotlinbasewithcorutine.ui.firstfragment

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daniyalirfan.kotlinbasewithcorutine.data.models.PostsResponse
import com.daniyalirfan.kotlinbasewithcorutine.data.remote.Resource
import com.daniyalirfan.kotlinbasewithcorutine.data.remote.reporitory.MainRepository
import com.daniyalirfan.kotlinbasewithcorutine.utils.NetworkHelper
import kotlinx.coroutines.launch


class FirstViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {


    private val _posts = MutableLiveData<Resource<PostsResponse>>()
    val postsData: LiveData<Resource<PostsResponse>>
        get() = _posts


    fun fetchPostsFromApi() {
        viewModelScope.launch {
            _posts.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                mainRepository.getPosts().let {
                    if (it.isSuccessful) {
                        _posts.postValue(Resource.success(it.body()!!))
                    } else _posts.postValue(Resource.error(it.message(), null))
                }
            } else _posts.postValue(Resource.error("No internet connection", null))
        }
    }

}