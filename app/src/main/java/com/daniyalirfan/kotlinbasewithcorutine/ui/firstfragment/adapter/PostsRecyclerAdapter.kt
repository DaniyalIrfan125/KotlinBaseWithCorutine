package com.daniyalirfan.kotlinbasewithcorutine.ui.firstfragment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.daniyalirfan.kotlinbasewithcorutine.R
import com.daniyalirfan.kotlinbasewithcorutine.data.models.PostsResponseItem


class PostsRecyclerAdapter(
    private val list: List<PostsResponseItem>,
    private val listener: ClickItemListener
) :
    RecyclerView.Adapter<PostsRecyclerAdapter.PostsViewHolder>() {

    interface ClickItemListener {
        fun onClicked(position: Int)
        fun onProductLiked(position: Int, isLiked: Boolean)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PostsViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val postsResponseItem: PostsResponseItem = list[position]
        holder.bind(postsResponseItem)


        holder.linearlayout!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                listener.onClicked(position)

            }
        })

        holder.mLikeButton!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                listener.onProductLiked(position, true)

            }
        })

    }

    override fun getItemCount(): Int = list.size


    class PostsViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_recyclerview, parent, false)) {
        private var tv_postsId: TextView? = null
        private var tv_title: TextView? = null
        private var tv_body: TextView? = null
        private var tv_userId: TextView? = null
        var mLikeButton: Button? = null
        var linearlayout: LinearLayout? = null


        init {
            tv_postsId = itemView.findViewById(R.id.tv_postsId)
            tv_title = itemView.findViewById(R.id.tv_title)
            tv_body = itemView.findViewById(R.id.tv_body)
            tv_userId = itemView.findViewById(R.id.tv_userId)
            linearlayout = itemView.findViewById(R.id.linearlayout)
            mLikeButton = itemView.findViewById(R.id.mLikeButton)
        }

        fun bind(post: PostsResponseItem) {
            tv_postsId?.text = "Post id : " + post.id.toString()
            tv_userId?.text = "User id : " + post.userId.toString()
            tv_body?.text = "Body : " + post.body
            tv_title?.text = "Title : " + post.title
        }

    }
}
