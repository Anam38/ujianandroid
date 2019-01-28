package com.example.root.ujianandroid.UI.Presenter.Activity

import com.example.root.ujianandroid.UI.Model.Home.ResultDataYoutube

interface PlaceView {

    fun onResult(data: List<ResultDataYoutube>)

    fun onError()
}