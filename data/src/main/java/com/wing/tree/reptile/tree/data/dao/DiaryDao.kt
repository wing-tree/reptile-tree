package com.wing.tree.reptile.tree.data.dao

import androidx.room.*
import com.wing.tree.reptile.tree.data.entity.Diary
import kotlinx.coroutines.flow.Flow

@Dao
interface DiaryDao {
    @Query("SELECT * FROM diary WHERE profileId = :profileId")
    fun diaryList(profileId: Long): Flow<List<Diary>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(diary: Diary)

    @Update
    suspend fun update(diary: Diary)

    @Delete
    suspend fun delete(diary: Diary)
}