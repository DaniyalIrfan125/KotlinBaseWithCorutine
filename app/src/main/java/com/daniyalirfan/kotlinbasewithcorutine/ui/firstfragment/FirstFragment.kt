package com.daniyalirfan.kotlinbasewithcorutine.ui.firstfragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.assessment.justcleandaniyal.data.models.PostsResponseItem
import com.daniyalirfan.kotlinbasewithcorutine.R
import com.daniyalirfan.kotlinbasewithcorutine.baseclasses.BaseFragment
import com.daniyalirfan.kotlinbasewithcorutine.data.remote.Resource
import com.daniyalirfan.kotlinbasewithcorutine.ui.firstfragment.adapter.PostsRecyclerAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class FirstFragment : BaseFragment() {

    private lateinit var viewModel: FirstViewModel
    private lateinit var adapter: PostsRecyclerAdapter
    private var mView: View? = null
    private var postsList: ArrayList<PostsResponseItem> = ArrayList()
    private var recycler_posts: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FirstViewModel::class.java)

        subscribeToNetworkLiveData()

        viewModel.fetchPosts()
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

        viewModel.users.observe(this, Observer {
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