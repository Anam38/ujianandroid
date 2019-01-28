package com.example.root.ujianandroid.UI.RoomDatabase.Favorite

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.root.ujianandroid.UI.Model.Favorite.FavoriteModel


@Database(entities = arrayOf(FavoriteModel::class), version = 1)
abstract class DataBaseFaforite : RoomDatabase() {
    abstract fun favorite(): FavoriteInterface
    companion object {
        fun getDatabaseFavorite(context: Context): DataBaseFaforite {

            val db = Room.databaseBuilder(
                context.applicationContext,
                DataBaseFaforite::class.java, "db.favorite"
            ).build()

            return db

        }
    }
}