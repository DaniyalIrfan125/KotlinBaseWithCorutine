package com.daniyalirfan.kotlinbasewithcorutine.presentation.activity.mainactivity

import com.daniyalirfan.kotlinbasewithcorutine.baseclasses.BaseViewModel
import com.daniyalirfan.kotlinbasewithcorutine.data.remote.repository.MainRepositoryImpl
import com.daniyalirfan.kotlinbasewithcorutine.utils.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepositoryImpl,
    private val networkHelper: NetworkHelper
) : BaseViewModel()
