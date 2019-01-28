package com.example.root.ujianandroid.UI.View.favorite

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.root.ujianandroid.R
import com.example.root.ujianandroid.UI.Model.Favorite.FavoriteModel
import com.example.root.ujianandroid.UI.Model.History.HistoryModel
import com.example.root.ujianandroid.UI.Presenter.fragment.onClickfavorite
import com.example.root.ujianandroid.UI.RoomDatabase.Favorite.DataBaseFaforite
import com.example.root.ujianandroid.UI.RoomDatabase.History.DataBasehistory
import com.example.root.ujianandroid.UI.View.detail.Main2Activity
import com.example.root.ujianandroid.UI.View.favorite.adapter.FavoriteAdapter1
import kotlinx.android.synthetic.main.fragment_favorite_list2.*
import kotlinx.android.synthetic.main.fragment_history_list.*
import kotlinx.android.synthetic.main.fragment_item_list.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

class FavoriteFragment : Fragment(){


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorite_list2, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDataFavorite()

        pullToRefreshfavorite.setOnRefreshListener {
            Handler(Looper.getMainLooper()).postDelayed({
                getDataFavorite()
                pullToRefreshfavorite.setRefreshing(false)
            },2000)
        }
    }

    private fun getDataFavorite() {
        async(UI){
            var data = bg {
                activity?.let { DataBaseFaforite.getDatabaseFavorite(it).favorite().getAll() }
            }
            showData(data.await()!!)
        }
    }

    private fun showData(await: List<FavoriteModel>) {
        recyclerviewfavorite.adapter = FavoriteAdapter1(await,object :onClickfavorite{
            override fun onItemClick(item: FavoriteModel,param: String) {
                if (param == "1"){

                    var data = HistoryModel()
                    data.image = item.image
                    data.title = item.title
                    data.deskripsi = item.deskripsi
                    data.channel = item.channel
                    data.time = item.time
                    data.idVideo = item.idVideo

                    checks(data, item.title)

                    startActivity<Main2Activity>(
                        "videoId" to item.idVideo.toString(),
                        "img" to item.image.toString(),
                        "title" to item.title.toString(),
                        "deskripsi" to item.deskripsi.toString(),
                        "channel" to item.channel.toString(),
                        "time" to item.time.toString()
                    )
                }else{
                    AlertDialog.Builder(activity).apply {
                        setCancelable(false)
                        setTitle("Hapus Favorite")
                        setMessage("Apakah anda Yakin Mau menghapus Data Ini")
                        setPositiveButton("Yes"){dialog, which ->
                            val data = FavoriteModel()
                            data.uid = item.uid

                            async(UI){
                                bg {
                                    DataBaseFaforite.getDatabaseFavorite(activity!!.applicationContext).favorite().delete(data)
                                }
                                toast("Delete Complete")
                                getDataFavorite()
                            }

                        }
                        setNeutralButton("No"){dialog, which ->

                        }
                    }.show()
                }
            }
        })
        recyclerviewfavorite.layoutManager = LinearLayoutManager(activity) as RecyclerView.LayoutManager?
    }

    private fun checks(data: HistoryModel, title: String?) {
        async(UI) {
            val cek = bg {
                activity?.let { DataBasehistory.getDatabaseHistory(it).history().loadbytitle(title.toString()) }
            }
            d("ghj",cek.await().toString())
            if (cek.await() == null){
                async(kotlinx.coroutines.experimental.android.UI) {
                    bg {
                        activity?.let { DataBasehistory.getDatabaseHistory(it).history().insertAll(data) }
                    }
                }
            }
        }
    }
    override fun onStop() {
        super.onStop()
    }
}
