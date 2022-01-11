package com.wing.tree.reptile.tree.data.datasource.local

import com.wing.tree.reptile.tree.data.database.Database
import com.wing.tree.reptile.tree.data.entity.Profile
import javax.inject.Inject

class ProfileDataSourceImpl @Inject constructor(
    private val database: Database
) : ProfileDataSource {
    override suspend fun profileList(): List<Profile> {
        return database.profileDao().profileList()
    }

    override suspend fun insert(profile: Profile) {
        database.profileDao().insert(profile)
    }

    override suspend fun update(profile: Profile) {
        database.profileDao().update(profile)
    }

    override suspend fun delete(profile: Profile) {
        database.profileDao().delete(profile)
    }
}