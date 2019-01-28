package com.example.root.ujianandroid.UI.RoomDatabase.History

import android.arch.persistence.room.*
import com.example.root.ujianandroid.UI.Model.History.HistoryModel

@Dao
interface HistoryInterface {

    @Query("SELECT * FROM historymodel ORDER BY uid DESC")
    fun getAll(): List<HistoryModel>

    @Query("SELECT * FROM historymodel WHERE uid = :historyid")
    fun loadAllByIds(historyid: Int): HistoryModel

    @Query("SELECT * FROM historymodel WHERE title = :title")
    fun loadbytitle(title: String): HistoryModel

    @Insert
    fun insertAll(vararg historyModel: HistoryModel)

    @Update
    fun update(vararg  historyModel: HistoryModel)

    @Delete
    fun delete(historyModel: HistoryModel)
}