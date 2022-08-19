package com.daniyalirfan.kotlinbasewithcorutine.ui.firstfragment

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.daniyalirfan.kotlinbasewithcorutine.MainApplication
import com.daniyalirfan.kotlinbasewithcorutine.R
import com.daniyalirfan.kotlinbasewithcorutine.baseclasses.BaseViewModel
import com.daniyalirfan.kotlinbasewithcorutine.data.models.PostsResponse
import com.daniyalirfan.kotlinbasewithcorutine.data.remote.Resource
import com.daniyalirfan.kotlinbasewithcorutine.data.remote.reporitory.MainRepository
import com.daniyalirfan.kotlinbasewithcorutine.utils.NetworkHelper
import com.daniyalirfan.kotlinbasewithcorutine.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirstViewModel @Inject constructor(
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
            _posts.postValue(Resource.loading())
            if (networkHelper.isNetworkConnected()) {
                mainRepository.getPosts()
                    .catch {
                        _posts.postValue(Resource.error(it.message!!))
                    }
                    .collect {
                        if (it.isSuccessful) {
                            _posts.postValue(Resource.success(it.body()!!))
                        } else {
                            _posts.postValue(Resource.error(it.message()))
                        }
                    }
            } else _posts.postValue(Resource.error(MainApplication.applicationContext.getString(R.string.no_internet_connection)))
        }
    }

    fun onClick() {
        btnClick.call()
    }

}