package com.example.root.ujianandroid.UI.RoomDatabase.History

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.root.ujianandroid.UI.Model.History.HistoryModel

@Database(entities = arrayOf(HistoryModel::class), version = 1)
abstract class DataBasehistory : RoomDatabase(){
    abstract fun history():HistoryInterface
    companion object {
        fun getDatabaseHistory(context: Context): DataBasehistory {

            val db = Room.databaseBuilder(
                context.applicationContext,
                DataBasehistory::class.java, "db.history"
            ).build()

            return db

        }
    }
}