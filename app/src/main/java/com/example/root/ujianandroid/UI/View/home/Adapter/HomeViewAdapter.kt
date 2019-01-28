package com.example.root.ujianandroid.UI.View.home.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.root.ujianandroid.R
import com.example.root.ujianandroid.UI.Model.Home.ItemsItem
import com.example.root.ujianandroid.UI.Presenter.Activity.onItemClick
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_item.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class HomeViewAdapter(
    private val mValues: List<ItemsItem>,
    val listener: onItemClick
) : RecyclerView.Adapter<HomeViewAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mIdView.text = item?.snippet?.title
        holder.mContentView.text = item?.snippet?.publishedAt
        holder.mAccount.text = item?.snippet?.channelTitle
        Picasso.get()
            .load(item.snippet?.thumbnails?.high?.url).error(R.drawable.gambar)
            .into(holder.mImages)
        Picasso.get()
            .load(item.snippet?.thumbnails?.medium?.url).error(R.drawable.gambar)
            .into(holder.mImgchannel)

        holder.itemView.onClick {
            listener.onItemClick(item)
        }

    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.title
        val mContentView: TextView = mView.time
        val mAccount: TextView = mView.channeltitle
        val mImages : ImageView = mView.images
        val mImgchannel : ImageView = mView.imgchannel

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }


}

