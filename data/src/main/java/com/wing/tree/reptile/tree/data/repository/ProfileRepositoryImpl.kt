package com.wing.tree.reptile.tree.data.repository

import com.wing.tree.reptile.tree.data.datasource.local.ProfileDataSource
import com.wing.tree.reptile.tree.data.mapper.ProfileMapper.toEntity
import com.wing.tree.reptile.tree.domain.model.Profile
import com.wing.tree.reptile.tree.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(private val dataSource: ProfileDataSource) : ProfileRepository {
    override suspend fun all(): List<Profile> {
        return dataSource.all()
    }

    override suspend fun insert(profile: Profile) {
        dataSource.insert(profile.toEntity())
    }
}