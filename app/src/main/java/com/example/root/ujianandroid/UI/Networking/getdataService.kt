package com.example.root.ujianandroid.UI.Networking

import com.example.root.ujianandroid.UI.Model.Home.ResultDataYoutube
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface getdataService {

    @GET("search")
    fun ambilData(
        @Query("part") part: String,
        @Query("maxResults") maxResults: String,
        @Query("q") q: String,
        @Query("key") key: String

    ) : Call<ResultDataYoutube>
}
