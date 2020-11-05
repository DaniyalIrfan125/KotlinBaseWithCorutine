package com.daniyalirfan.kotlinbasewithcorutine.ui.activity

import androidx.hilt.lifecycle.ViewModelInject
import com.daniyalirfan.kotlinbasewithcorutine.baseclasses.BaseViewModel
import com.daniyalirfan.kotlinbasewithcorutine.data.remote.reporitory.MainRepository
import com.daniyalirfan.kotlinbasewithcorutine.utils.NetworkHelper

class MainViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : BaseViewModel() {


}
