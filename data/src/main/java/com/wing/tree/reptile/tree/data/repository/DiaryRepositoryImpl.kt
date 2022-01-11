package com.wing.tree.reptile.tree.data.repository

import com.wing.tree.reptile.tree.data.datasource.local.DiaryDataSource
import com.wing.tree.reptile.tree.data.mapper.DiaryMapper.toEntity
import com.wing.tree.reptile.tree.domain.model.Diary
import com.wing.tree.reptile.tree.domain.repository.DiaryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DiaryRepositoryImpl @Inject constructor(private val dataSource: DiaryDataSource) : DiaryRepository {
    override fun diaryList(profileId: Long): Flow<List<Diary>> {
        return dataSource.diaryList(profileId)
    }

    override suspend fun insert(diary: Diary) {
        dataSource.insert(diary.toEntity())
    }
}