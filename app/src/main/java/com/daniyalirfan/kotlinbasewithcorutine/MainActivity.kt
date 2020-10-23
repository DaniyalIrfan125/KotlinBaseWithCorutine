package com.daniyalirfan.kotlinbasewithcorutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import com.daniyalirfan.kotlinbasewithcorutine.baseclasses.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

   lateinit var progress_bar : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialising()
    }

    private fun initialising() {
        progress_bar = findViewById(R.id.progress_bar)
    }

   open fun showProgressBar(){
        progress_bar.visibility = View.VISIBLE
    }

    open fun hideProgressBar(){
        progress_bar.visibility = View.GONE
    }

}