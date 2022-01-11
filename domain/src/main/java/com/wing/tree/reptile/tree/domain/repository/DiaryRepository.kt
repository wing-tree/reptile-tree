package com.wing.tree.reptile.tree.domain.repository

import com.wing.tree.reptile.tree.domain.model.Diary
import kotlinx.coroutines.flow.Flow

interface DiaryRepository {
    fun diaryList(profileId: Long): Flow<List<Diary>>
    suspend fun insert(diary: Diary)
}