package com.example.root.ujianandroid.UI.View.detail.Adapter

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
import kotlinx.android.synthetic.main.fragment_detail.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class DetailAdapter(
    private val mValues: List<ItemsItem>,val listener :onItemClick
) : RecyclerView.Adapter<DetailAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_detail, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mIdView.text = item.snippet?.title
        holder.mContentView.text = item.snippet?.channelTitle
        Picasso.get()
            .load(item.snippet?.thumbnails?.high?.url).error(R.drawable.gambar)
            .into(holder.mImg)

        holder.itemView.onClick {
            listener.onItemClick(item)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.titledetaillist
        val mContentView: TextView = mView.channeldetaillist
        val mImg : ImageView = mView.imgdetaillist

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
