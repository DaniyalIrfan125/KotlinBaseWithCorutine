package com.daniyalirfan.kotlinbasewithcorutine.baseclasses

class BaseEvent<out T>(internal val data: T?) {

    private var hasBeenHandled = false

    /**
     * Returns the content and prevents its use again.
     */
    fun getEventIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            data
        }
    }
}