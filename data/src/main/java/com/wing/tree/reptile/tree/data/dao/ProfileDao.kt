package com.wing.tree.reptile.tree.data.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.wing.tree.reptile.tree.data.entity.Profile

@Dao
interface ProfileDao {
    @Query("SELECT * FROM profile")
    fun profileList(): List<Profile>

    @Insert(onConflict = REPLACE)
    suspend fun insert(profile: Profile)

    @Update
    suspend fun update(profile: Profile)

    @Delete
    suspend fun delete(profile: Profile)
}