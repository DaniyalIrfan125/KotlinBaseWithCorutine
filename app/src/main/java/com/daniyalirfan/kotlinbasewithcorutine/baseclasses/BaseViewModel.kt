package com.daniyalirfan.kotlinbasewithcorutine.baseclasses

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


open class BaseViewModel : ViewModel() {

    protected val _dataEvent: MutableLiveData<BaseEvent<BaseDataEvents>> = MutableLiveData()
    val obDataEvent: LiveData<BaseEvent<BaseDataEvents>> = _dataEvent

    protected fun showLoader(progressText: String = "") {
        _dataEvent.value = BaseEvent(BaseDataEvents.ShowLoader(progressText))
    }

    protected fun hideLoader() {
        _dataEvent.value = BaseEvent(BaseDataEvents.HideLoader)
    }
}