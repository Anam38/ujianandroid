package com.example.root.ujianandroid.UI.View.favorite.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.root.ujianandroid.R
import com.example.root.ujianandroid.UI.Model.Favorite.FavoriteModel
import com.example.root.ujianandroid.UI.Presenter.fragment.onClickfavorite
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_favorite.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.sdk25.coroutines.onLongClick

class FavoriteAdapter1(
    private val mValues: List<FavoriteModel>, val click : onClickfavorite
) : RecyclerView.Adapter<FavoriteAdapter1.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_favorite, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mIdView.text = item.title
        holder.mContentView.text = item.channel
        Picasso.get()
            .load(item.image).error(R.drawable.gambar)
            .into(holder.mImg)
        holder.itemView.onClick {
            click.onItemClick(item,"1")
        }
        holder.itemView.onLongClick {
            click.onItemClick(item,"2")
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.titlefavorite
        val mContentView: TextView = mView.channelfavorite
        val mImg : ImageView = mView.imgfavorite

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
