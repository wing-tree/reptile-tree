package com.wing.tree.reptile.tree.domain.repository

import com.wing.tree.reptile.tree.domain.model.Profile

interface ProfileRepository {
    suspend fun profileList(): List<Profile>
    suspend fun insert(profile: Profile)
}