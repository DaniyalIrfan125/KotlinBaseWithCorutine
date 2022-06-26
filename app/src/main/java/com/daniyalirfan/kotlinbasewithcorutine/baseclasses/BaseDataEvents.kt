package com.daniyalirfan.kotlinbasewithcorutine.baseclasses
sealed class BaseDataEvents {
    class ShowLoader(val progressText: String = "") : BaseDataEvents()
    object HideLoader : BaseDataEvents()
    class Exception(val message: String) : BaseDataEvents()
    class Error(val message: String) : BaseDataEvents()
    class Toast(val message: String) : BaseDataEvents()
    object ForceLogout : BaseDataEvents()
}