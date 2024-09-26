package com.daniyalirfan.kotlinbasewithcorutine.ui.firstfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.daniyalirfan.kotlinbasewithcorutine.baseclasses.BaseViewModel
import com.daniyalirfan.kotlinbasewithcorutine.data.models.PostsResponse
import com.daniyalirfan.kotlinbasewithcorutine.utils.Resource
import com.daniyalirfan.kotlinbasewithcorutine.domain.usecases.NoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirstViewModel @Inject constructor(
        private val noteUseCase: NoteUseCase
) : BaseViewModel() {


    private val _posts = MutableLiveData<Resource<PostsResponse>>()
    val postsData: LiveData<Resource<PostsResponse>>
        get() = _posts

    fun fetchPostsFromApi() {
        viewModelScope.launch {
            showLoader("Loading...")
            try{
                val result = noteUseCase.fetchPostsFromApi()
                _posts.postValue(result)
            }catch (e:Exception){
                e.printStackTrace()
            }finally {
                hideLoader()
            }
        }
    }

}