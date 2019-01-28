package com.example.root.ujianandroid.UI.RoomDatabase.setup

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.root.ujianandroid.UI.Model.setup.SetupModel
import com.example.root.ujianandroid.UI.RoomDatabase.Favorite.Setup
import com.example.root.ujianandroid.UI.RoomDatabase.History.DataBasehistory

@Database(entities = arrayOf(SetupModel::class), version = 1)
abstract class DataBaseSetup:RoomDatabase() {
    abstract fun setup(): Setup
    companion object {
        fun getDatabaseSetup(context: Context): DataBaseSetup {

            val db = Room.databaseBuilder(
                context.applicationContext,
                DataBaseSetup::class.java, "db.setup"
            ).build()

            return db

        }
    }
}