package com.daniyalirfan.kotlinbasewithcorutine.presentation.fragments.firstfragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.daniyalirfan.kotlinbasewithcorutine.BR
import com.daniyalirfan.kotlinbasewithcorutine.R
import com.daniyalirfan.kotlinbasewithcorutine.baseclasses.BaseFragment
import com.daniyalirfan.kotlinbasewithcorutine.data.local.datastore.DataStoreProvider
import com.daniyalirfan.kotlinbasewithcorutine.data.models.PostsResponseItem
import com.daniyalirfan.kotlinbasewithcorutine.data.remote.Resource
import com.daniyalirfan.kotlinbasewithcorutine.databinding.FirstFragmentBinding
import com.daniyalirfan.kotlinbasewithcorutine.presentation.fragments.firstfragment.adapter.PostsRecyclerAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class FirstFragment : BaseFragment<FirstFragmentBinding, FirstViewModel>() {

    override val layoutId: Int
        get() = R.layout.first_fragment
    override val viewModel: Class<FirstViewModel>
        get() = FirstViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel

    private lateinit var adapter: PostsRecyclerAdapter
    private var postsList: ArrayList<PostsResponseItem> = ArrayList()

    @Inject
    lateinit var dataStoreProvider: DataStoreProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        subscribeToObserveDataStore()

        //calling api
        mViewModel.fetchPostsFromApi()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialising()
    }

    override fun subscribeToViewLiveData() {
        super.subscribeToViewLiveData()

//        mViewDataBinding.btn.setOnClickListener {
//            //observing data from edittext
//            mViewModel.myedittext.get()?.let {
//
//                //setting data to textview
//                mViewModel.myName.set(it)
//
//                //saving data to data store
//                //Stores the values
//                lifecycleScope.launch {
//                    dataStoreProvider.storeData(false, it)
//                }
//            }
//        }
        mViewModel.btnClick.observe(viewLifecycleOwner) {
            if (it) {
                //observing data from edittext
                mViewModel.myedittext.get()?.let {

                    //setting data to textview
                    mViewModel.myName.set(it)

                    //saving data to data store
                    //Stores the values
                    lifecycleScope.launch {
                        dataStoreProvider.storeData(false, it)
                    }


                }
            }
        }
    }

    private fun subscribeToObserveDataStore() {

        //observing data from data store and showing
        dataStoreProvider.userNameFlow.asLiveData().observe(this) {
            mViewModel.myName.set(it)
        }

    }


    private fun initialising() {

        adapter = PostsRecyclerAdapter(postsList, object : PostsRecyclerAdapter.ClickItemListener {
            override fun onClicked(position: Int) {
                Navigation.findNavController(mViewDataBinding.recyclerPosts)
                    .navigate(R.id.action_firstFragment_to_secondFragment)
            }

            override fun onProductLiked(position: Int, isLiked: Boolean) {
            }

        })

        mViewDataBinding.recyclerPosts.layoutManager = LinearLayoutManager(requireContext())
        mViewDataBinding.recyclerPosts.adapter = adapter

    }

    //subscribing to network live data
    override fun subscribeToNetworkLiveData() {
        super.subscribeToNetworkLiveData()

        mViewModel.postsData.observe(this) {
            when (it) {
                is Resource.Success -> {
                    hideProgressBar()
                    it.data.let {
                        postsList.addAll(it)
                        adapter.notifyDataSetChanged()
                    }

                }

                is Resource.Loading -> {
                    showProgressBar()
                }

                is Resource.Error -> {
                    hideProgressBar()

                    Snackbar.make(mViewDataBinding.recyclerPosts, it.message, Snackbar.LENGTH_SHORT)
                        .show()

                }
            }
        }
    }


}