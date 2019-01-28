package com.example.root.ujianandroid.UI.View.history.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.root.ujianandroid.R
import com.example.root.ujianandroid.UI.Model.History.HistoryModel
import com.example.root.ujianandroid.UI.Presenter.fragment.onClickHistory
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_history.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.sdk25.coroutines.onLongClick

class HistoryAdapter(
    private val mValues: List<HistoryModel>,val click : onClickHistory
) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_history, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mTitle.text = item.title
        holder.mchannel.text = item.channel
        Picasso.get()
            .load(item.image).error(R.drawable.gambar)
            .into(holder.mImg)

        holder.itemView.onClick {
            click.onitemclick(item,"1")
        }
        holder.itemView.onLongClick {
            click.onitemclick(item,"2")
        }

    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mTitle: TextView = mView.titlehistory
        val mchannel: TextView = mView.channelhistory
        val mImg : ImageView = mView.imghistory

        override fun toString(): String {
            return super.toString() + " '" + mchannel.text + "'"
        }
    }
}
