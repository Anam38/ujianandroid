package com.example.root.ujianandroid.UI.View.detail

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.root.ujianandroid.R
import com.example.root.ujianandroid.UI.Model.Favorite.FavoriteModel
import com.example.root.ujianandroid.UI.Model.History.HistoryModel
import com.example.root.ujianandroid.UI.Model.Home.ItemsItem
import com.example.root.ujianandroid.UI.Model.Home.ResultDataYoutube
import com.example.root.ujianandroid.UI.Presenter.Activity.PlacePresenter
import com.example.root.ujianandroid.UI.Presenter.Activity.PlaceView
import com.example.root.ujianandroid.UI.Presenter.Activity.onItemClick
import com.example.root.ujianandroid.UI.RoomDatabase.Favorite.DataBaseFaforite
import com.example.root.ujianandroid.UI.RoomDatabase.History.DataBasehistory
import com.example.root.ujianandroid.UI.View.detail.Adapter.DetailAdapter
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.customebardetail.*
import kotlinx.android.synthetic.main.detail_activity.*
import kotlinx.android.synthetic.main.fragment_item.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.toast

class Main2Activity : YouTubeBaseActivity(), PlaceView {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)

        val videoId = intent.getStringExtra("videoId")
        val img = intent.getStringExtra("img")
        val title = intent.getStringExtra("title")
        val deskripsi = intent.getStringExtra("deskripsi")
        val channel = intent.getStringExtra("channel")
        val time = intent.getStringExtra("time")

        titledetail.text = title
        deskripsidetail.text = deskripsi
        channeltitledetail.text = channel
        timedetail.text = time
        Picasso.get()
            .load(img).error(R.drawable.gambar)
            .into(imgchanneldetail)

        viewvideo.initialize("AIzaSyDYC5JR9GuhM7O3qSllyLruHq1g9vI_OUg",
            object : OnInitializedListener {
                override fun onInitializationSuccess(
                    provider: YouTubePlayer.Provider,
                    youTubePlayer: YouTubePlayer, b: Boolean
                ) {

                    // do any work here to cue video, play video, etc.
                    youTubePlayer.loadVideo(videoId)
                }

                override fun onInitializationFailure(
                    provider: YouTubePlayer.Provider,
                    youTubeInitializationResult: YouTubeInitializationResult
                ) {

                }
            })

        showmoreVideo(title)

        backdetail.onClick {
            finish()
        }

        favorite.onClick {
            saveDataRoom(videoId,img,title,deskripsi,channel,time)
            toast("Add To Favorite Complete")
        }
    }

    private fun showmoreVideo(title: String?) {
        val presenter =  PlacePresenter(this)
        presenter.loadData("snippet", "10", title.toString(), getString(R.string.KEY))
    }

    override fun onResult(data: List<ResultDataYoutube>) {
        recyclerviewdetail.adapter = DetailAdapter(data as List<ItemsItem>,object :onItemClick{
            override fun onItemClick(item: ItemsItem) {
                var data = HistoryModel()
                data.image = item.snippet?.thumbnails?.high?.url
                data.title = item.snippet?.title
                data.deskripsi = item.snippet?.description
                data.channel = item.snippet?.channelTitle
                data.time = item.snippet?.publishedAt
                data.idVideo = item.id?.videoId

                saveData(data)

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
        recyclerviewdetail.layoutManager = LinearLayoutManager(this)
    }

    private fun saveData(data: HistoryModel) {

        async(kotlinx.coroutines.experimental.android.UI) {
            bg {
                DataBasehistory.getDatabaseHistory(this@Main2Activity).history().insertAll(data)
            }
        }
    }

    override fun onError() {
        toast("Mohon Periksa Jaringan Anda")
    }


    private fun saveDataRoom(
        videoId: String?,
        img: String?,
        title: String?,
        deskripsi: String?,
        channel: String?,
        time: String?
    ) {

        val faforite = FavoriteModel()
        faforite.idVideo = videoId
        faforite.image = img
        faforite.title = title
        faforite.deskripsi = deskripsi
        faforite.channel = channel
        faforite.time = time

        async(UI) {
            bg {
                DataBaseFaforite.getDatabaseFavorite(this@Main2Activity).favorite().insertAll(faforite)
            }
        }
    }
}
