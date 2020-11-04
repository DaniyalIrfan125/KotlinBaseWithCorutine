package com.daniyalirfan.kotlinbasewithcorutine.ui.firstfragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.daniyalirfan.kotlinbasewithcorutine.R
import com.daniyalirfan.kotlinbasewithcorutine.baseclasses.BaseFragment
import com.daniyalirfan.kotlinbasewithcorutine.data.local.datastore.DataStoreProvider
import com.daniyalirfan.kotlinbasewithcorutine.data.models.PostsResponseItem
import com.daniyalirfan.kotlinbasewithcorutine.data.remote.Resource
import com.daniyalirfan.kotlinbasewithcorutine.ui.firstfragment.adapter.PostsRecyclerAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FirstFragment : BaseFragment() {

    private lateinit var viewModel: FirstViewModel
    private lateinit var adapter: PostsRecyclerAdapter
    private var mView: View? = null
    private var postsList: ArrayList<PostsResponseItem> = ArrayList()
    private var recycler_posts: RecyclerView? = null
    lateinit var dataStoreProvider: DataStoreProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FirstViewModel::class.java)


        //Get reference to our Data Store Provider class
        dataStoreProvider = DataStoreProvider(requireContext())


        subscribeToNetworkLiveData()
        subscribeToObserveDataStore()

        //calling api
        viewModel.fetchPostsFromApi()
    }

    private fun subscribeToObserveDataStore() {

        dataStoreProvider.userNameFlow.asLiveData().observe(this, Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })

        //saving data to data store

        //Stores the values
        GlobalScope.launch {
            dataStoreProvider.storeData(false,"daniyal testing")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.first_fragment, container, false)

        initialising()
        return mView
    }

    private fun initialising() {
        recycler_posts = mView!!.findViewById(R.id.recycler_posts)


        adapter = PostsRecyclerAdapter(postsList, object : PostsRecyclerAdapter.ClickItemListener {
            override fun onClicked(position: Int) {

            }

            override fun onProductLiked(position: Int, isLiked: Boolean) {
            }

        })

        recycler_posts!!.layoutManager = LinearLayoutManager(requireContext())
        recycler_posts!!.adapter = adapter
    }

    override fun subscribeToNetworkLiveData() {
        super.subscribeToNetworkLiveData()

        viewModel.postsData.observe(this, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    hideProgressBar()
                    it.data?.let {
                        postsList.addAll(it)
                        adapter.notifyDataSetChanged()
                    }

                }
                Resource.Status.LOADING -> {
                    showProgressBar()
                }
                Resource.Status.ERROR -> {
                    hideProgressBar()

                    Snackbar.make(recycler_posts!!, it.message!!, Snackbar.LENGTH_SHORT)
                        .show()

                }
            }
        })
    }


}