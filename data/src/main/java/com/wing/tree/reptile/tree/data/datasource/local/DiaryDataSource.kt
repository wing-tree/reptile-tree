package com.wing.tree.reptile.tree.data.datasource.local

import com.wing.tree.reptile.tree.data.entity.Diary
import kotlinx.coroutines.flow.Flow

interface DiaryDataSource {
    fun diaryList(id: Long): Flow<List<Diary>>
    suspend fun insert(diary: Diary)
    suspend fun update(diary: Diary)
    suspend fun delete(diary: Diary)
}