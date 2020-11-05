package com.daniyalirfan.kotlinbasewithcorutine.baseclasses

import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProviders
import com.daniyalirfan.kotlinbasewithcorutine.SharedViewModel


abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel> : AppCompatActivity() {

    private lateinit var mViewDataBinding: T
    protected lateinit var mViewModel: V

    /**
     * viewModel variable that will get value from activity which it will implement this
     * we will use this variable viewModel to bind with view through databinding
     */
    abstract val viewModel: Class<V>

    /**
     * layoutId variable to get layout value from activity which will implement this layoutId
     * we will use this layoutId for databinding
     */
    @get:LayoutRes
    abstract val layoutId: Int

    /**
     * bindingVariable which will bind with view
     */

    abstract val bindingVariable: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databindingWithViewModel()
    }


    /**
     * Function to perform databinding and attaching viewmodel with view
     */
    private fun databindingWithViewModel() {
        mViewDataBinding = DataBindingUtil.setContentView(this, layoutId)
        mViewModel = ViewModelProviders.of(this).get(viewModel)
        mViewDataBinding.setVariable(bindingVariable, mViewModel)
        mViewDataBinding.executePendingBindings()

    }

}