package com.daniyalirfan.kotlinbasewithcorutine.ui.secondfragment

import com.daniyalirfan.kotlinbasewithcorutine.BR
import com.daniyalirfan.kotlinbasewithcorutine.R
import com.daniyalirfan.kotlinbasewithcorutine.baseclasses.BaseFragment
import com.daniyalirfan.kotlinbasewithcorutine.databinding.LayoutSecondBinding
import com.daniyalirfan.kotlinbasewithcorutine.ui.firstfragment.FirstViewModel

class SecondFragment : BaseFragment<LayoutSecondBinding, SecondViewModel>() {

    override val layoutId: Int
        get() = R.layout.layout_second
    override val viewModel: Class<SecondViewModel>
        get() = SecondViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel
}