package com.example.root.ujianandroid.UI.View.history

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.root.ujianandroid.R
import com.example.root.ujianandroid.UI.Model.History.HistoryModel
import com.example.root.ujianandroid.UI.Presenter.fragment.onClickHistory
import com.example.root.ujianandroid.UI.RoomDatabase.History.DataBasehistory
import com.example.root.ujianandroid.UI.View.detail.Main2Activity
import com.example.root.ujianandroid.UI.View.history.adapter.HistoryAdapter
import kotlinx.android.synthetic.main.fragment_history_list.*
import kotlinx.android.synthetic.main.fragment_item_list.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

class HistoryFragment : Fragment(){


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getDataHistory()
        pullToRefreshhistory.setOnRefreshListener{
            Handler(Looper.getMainLooper()).postDelayed({
                getDataHistory()
                pullToRefreshhistory.setRefreshing(false)
            },2000)
        }
    }

    private fun getDataHistory() {
        async(UI){

            var data = bg {
                activity?.let { DataBasehistory.getDatabaseHistory(it).history().getAll() }
            }
            data.await()?.let { showData(it) }
        }
    }

    private fun showData(await: List<HistoryModel>) {
        recyclerviewHistory.adapter = HistoryAdapter(await,object :onClickHistory{
            override fun onitemclick(item: HistoryModel, param: String) {
                if (param == "1") {

                    var data = HistoryModel()
                    data.image = item.image
                    data.title = item.title
                    data.deskripsi = item.deskripsi
                    data.channel = item.channel
                    data.time = item.time
                    data.idVideo = item.idVideo

                    var uid = HistoryModel()
                    uid.uid = item.uid

                    saveData(uid,data)

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
                        setTitle("Hapus History")
                        setMessage("Apakah anda Yakin Mau menghapus Data Ini")
                        setPositiveButton("Yes"){dialog, which ->
                            val data = HistoryModel()
                            data.uid = item.uid

                            async(UI){
                                bg {
                                    DataBasehistory.getDatabaseHistory(activity!!.applicationContext).history().delete(data)
                                }
                                    toast("Delete Berhasil")
                                    getDataHistory()
                            }
                        }
                        setNeutralButton("No"){dialog, which ->

                        }
                    }.show()
                }
            }
        })
        recyclerviewHistory.layoutManager = LinearLayoutManager(activity)
    }


    private fun saveData(
        data: HistoryModel,
        data1: HistoryModel
    ) {
        doAsync {
            bg {
                activity?.let { DataBasehistory.getDatabaseHistory(it).history().delete(data) }
            }
        }
        doAsync{
            bg {
                DataBasehistory.getDatabaseHistory(activity!!.applicationContext).history().insertAll(data1)
            }
        }
    }
    override fun onStop() {
        super.onStop()
    }
}
