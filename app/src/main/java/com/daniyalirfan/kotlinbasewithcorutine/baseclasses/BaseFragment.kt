package com.daniyalirfan.kotlinbasewithcorutine.baseclasses

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.daniyalirfan.kotlinbasewithcorutine.MainActivity


open class BaseFragment : Fragment() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeToShareLiveData()
        subscribeToNavigationLiveData()
    }


    open fun subscribeToShareLiveData() {

    }

    open fun subscribeToNetworkLiveData() {
        //All Network Tasks
    }

    open fun subscribeToNavigationLiveData() {

    }

    open fun showProgressBar(){
        (activity as MainActivity).showProgressBar()
    }

    open fun hideProgressBar(){
        (activity as MainActivity).hideProgressBar()
    }
}