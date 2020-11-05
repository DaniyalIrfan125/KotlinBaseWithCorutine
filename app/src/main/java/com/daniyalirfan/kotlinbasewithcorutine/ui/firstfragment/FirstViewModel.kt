package com.daniyalirfan.kotlinbasewithcorutine.ui.firstfragment

import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.daniyalirfan.kotlinbasewithcorutine.baseclasses.BaseViewModel
import com.daniyalirfan.kotlinbasewithcorutine.data.models.PostsResponse
import com.daniyalirfan.kotlinbasewithcorutine.data.remote.Resource
import com.daniyalirfan.kotlinbasewithcorutine.data.remote.reporitory.MainRepository
import com.daniyalirfan.kotlinbasewithcorutine.utils.NetworkHelper
import com.daniyalirfan.kotlinbasewithcorutine.utils.SingleLiveEvent
import kotlinx.coroutines.launch


class FirstViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : BaseViewModel() {


    private val _posts = MutableLiveData<Resource<PostsResponse>>()
    val postsData: LiveData<Resource<PostsResponse>>
        get() = _posts

    val myName = ObservableField<String>()
    val myedittext = ObservableField<String>()
    val btnClick = SingleLiveEvent<Any>()


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

    fun onClick() {
        btnClick.call()
    }

}