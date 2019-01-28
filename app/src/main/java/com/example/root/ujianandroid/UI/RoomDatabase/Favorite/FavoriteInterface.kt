package com.example.root.ujianandroid.UI.RoomDatabase.Favorite

import android.arch.persistence.room.*
import com.example.root.ujianandroid.UI.Model.Favorite.FavoriteModel


@Dao
interface FavoriteInterface {

    @Query("SELECT * FROM favoritemodel ORDER BY uid DESC")
    fun getAll(): List<FavoriteModel>

    @Query("SELECT * FROM favoritemodel WHERE uid = :favoriteid")
    fun loadAllByIds(favoriteid: Int): FavoriteModel

    @Insert
    fun insertAll(vararg favoriteModel: FavoriteModel)

    @Update
    fun update(vararg  favoriteModel: FavoriteModel)

    @Delete
    fun delete(favoriteModel: FavoriteModel)
}