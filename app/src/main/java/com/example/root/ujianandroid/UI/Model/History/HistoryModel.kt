package com.example.root.ujianandroid.UI.Model.History

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

@Entity
class HistoryModel :Serializable{

    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
    @ColumnInfo(name = "image")
    var image: String? = null
    @ColumnInfo(name = "title")
    var title: String? = null
    @ColumnInfo(name = "deskripsi")
    var deskripsi: String? = null
    @ColumnInfo(name = "channel")
    var channel: String? = null
    @ColumnInfo(name = "time")
    var time: String? = null
    @ColumnInfo(name = "idVideo")
    var idVideo: String? = null

}