package com.daniyalirfan.kotlinbasewithcorutine.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil.setContentView
import com.daniyalirfan.kotlinbasewithcorutine.BR
import com.daniyalirfan.kotlinbasewithcorutine.R
import com.daniyalirfan.kotlinbasewithcorutine.baseclasses.BaseActivity
import com.daniyalirfan.kotlinbasewithcorutine.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val viewModel: Class<MainViewModel>
        get() = MainViewModel::class.java

    override val layoutId: Int
        get() = R.layout.activity_main

    override val bindingVariable: Int
        get() = BR.viewModel

    lateinit var progress_bar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialising()
    }

    private fun initialising() {
        progress_bar = findViewById(R.id.progress_bar)
    }

    open fun showProgressBar() {
        progress_bar.visibility = View.VISIBLE
    }

    open fun hideProgressBar() {
        progress_bar.visibility = View.GONE
    }

}