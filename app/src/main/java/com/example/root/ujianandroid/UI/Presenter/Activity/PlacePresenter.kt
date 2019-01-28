package com.example.root.ujianandroid.UI.Presenter.Activity

import android.util.Log
import com.example.root.ujianandroid.UI.Model.Home.ResultDataYoutube
import com.example.root.ujianandroid.UI.Networking.ConfigNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PlacePresenter(val view: PlaceView) {

    fun loadData(part: String, maxResults: String, q: String, key: String) {

        ConfigNetwork.getservice().ambilData(part, maxResults, q, key).enqueue(object : Callback<ResultDataYoutube> {
            override fun onFailure(call: Call<ResultDataYoutube>, t: Throwable) {
                Log.d("waaaww", t.message)
            }

            override fun onResponse(call: Call<ResultDataYoutube>, response: Response<ResultDataYoutube>) {
                if (response.isSuccessful) {
                    val data = response.body()?.items

                    view.onResult(data as List<ResultDataYoutube>)
                } else {
                    view.onError()
                }
            }
        })

    }
}