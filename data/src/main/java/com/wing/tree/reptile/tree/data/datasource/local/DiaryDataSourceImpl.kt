package com.wing.tree.reptile.tree.data.datasource.local

import com.wing.tree.reptile.tree.data.database.Database
import com.wing.tree.reptile.tree.data.entity.Diary
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DiaryDataSourceImpl @Inject constructor(
    private val database: Database
) : DiaryDataSource {
    override fun diaryList(id: Long): Flow<List<Diary>> {
        return database.diaryDao().diaryList(id)
    }

    override suspend fun insert(diary: Diary) {
        database.diaryDao().insert(diary)
    }

    override suspend fun update(diary: Diary) {
        database.diaryDao().update(diary)
    }

    override suspend fun delete(diary: Diary) {
        database.diaryDao().delete(diary)
    }
}