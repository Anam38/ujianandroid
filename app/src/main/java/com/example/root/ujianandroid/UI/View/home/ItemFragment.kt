package com.example.root.ujianandroid.UI.View.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.root.ujianandroid.R
import com.example.root.ujianandroid.UI.Model.History.HistoryModel
import com.example.root.ujianandroid.UI.View.home.Adapter.HomeViewAdapter
import com.example.root.ujianandroid.UI.Model.Home.ItemsItem
import com.example.root.ujianandroid.UI.Model.Home.ResultDataYoutube
import com.example.root.ujianandroid.UI.Presenter.Activity.PlacePresenter
import com.example.root.ujianandroid.UI.Presenter.Activity.PlaceView
import com.example.root.ujianandroid.UI.Presenter.Activity.onItemClick
import com.example.root.ujianandroid.UI.RoomDatabase.History.DataBasehistory
import com.example.root.ujianandroid.UI.View.detail.Main2Activity
import kotlinx.android.synthetic.main.fragment_item_list.*
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import android.support.v4.widget.SwipeRefreshLayout
import android.util.Log.d
import kotlinx.android.synthetic.main.fragment_history_list.*
import org.jetbrains.anko.support.v4.alert


class ItemFragment : Fragment(), PlaceView, SwipeRefreshLayout.OnRefreshListener {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var inten = activity?.intent?.getStringExtra("param")

        if(inten != null){
            inten?.let { callPrisenter("snippet", "50", it, getString(R.string.KEY)) }
        }
        else{
            callPrisenter("snippet", "50", "", getString(R.string.KEY))
        }

        pullToRefresh.setOnRefreshListener(this)

    }

    private fun callPrisenter(s: String, s1: String, s2: String, string: String) {
        val presenter = PlacePresenter(this)
        presenter.loadData(s, s1, s2, string)

    }

    override fun onRefresh() {
        Handler(Looper.getMainLooper()).postDelayed({
            pullToRefresh.setRefreshing(false)
        },2000)
    }


    override fun onResult(data: List<ResultDataYoutube>) {
        recyclerview.adapter = HomeViewAdapter(data as List<ItemsItem>, object :
            onItemClick {
            override fun onItemClick(item: ItemsItem) {

                var data = HistoryModel()
                data.image = item.snippet?.thumbnails?.high?.url
                data.title = item.snippet?.title
                data.deskripsi = item.snippet?.description
                data.channel = item.snippet?.channelTitle
                data.time = item.snippet?.publishedAt
                data.idVideo = item.id?.videoId

                checks(item.snippet?.title, data)

                startActivity<Main2Activity>(
                    "videoId" to item.id?.videoId.toString(),
                    "img" to item.snippet?.thumbnails?.high?.url.toString(),
                    "title" to item.snippet?.title.toString(),
                    "deskripsi" to item.snippet?.description.toString(),
                    "channel" to item.snippet?.channelTitle.toString(),
                    "time" to item.snippet?.publishedAt.toString()
                )
            }
        })
        recyclerview.layoutManager = LinearLayoutManager(context) as RecyclerView.LayoutManager?
    }

    private fun checks(title: String?, data: HistoryModel){
        async(kotlinx.coroutines.experimental.android.UI) {
           var check =  bg {
                activity?.let { DataBasehistory.getDatabaseHistory(it).history().loadbytitle(title.toString())}
            }
            if (check.await() != null){
                async(kotlinx.coroutines.experimental.android.UI) {
                    bg {
                        activity?.let { DataBasehistory.getDatabaseHistory(it).history().update(data) }
                    }
                }
            }else{
                async(kotlinx.coroutines.experimental.android.UI) {
                    bg {
                        activity?.let { DataBasehistory.getDatabaseHistory(it).history().insertAll(data) }
                    }
                }
            }
        }
    }

    override fun onError() {
        toast("Mohon periksa jaringan anda")
    }

    override fun onStop() {
        super.onStop()
    }
}
