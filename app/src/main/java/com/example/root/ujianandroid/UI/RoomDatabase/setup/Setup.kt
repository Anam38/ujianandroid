package com.example.root.ujianandroid.UI.RoomDatabase.Favorite

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.root.ujianandroid.UI.Model.Favorite.FavoriteModel
import com.example.root.ujianandroid.UI.Model.setup.SetupModel

@Dao
interface Setup {

    @Query("SELECT * FROM setupmodel ORDER BY uid DESC")
    fun getAll(): List<SetupModel>

    @Insert
    fun insertAll(vararg setupModel: SetupModel)

}