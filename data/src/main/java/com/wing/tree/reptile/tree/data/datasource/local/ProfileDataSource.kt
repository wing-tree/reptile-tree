package com.wing.tree.reptile.tree.data.datasource.local

import com.wing.tree.reptile.tree.data.entity.Profile

interface ProfileDataSource {
    suspend fun profileList(): List<Profile>
    suspend fun insert(profile: Profile)
    suspend fun update(profile: Profile)
    suspend fun delete(profile: Profile)
}