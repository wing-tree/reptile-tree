package com.wing.tree.reptile.tree.data.entity

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wing.tree.reptile.tree.domain.model.Profile

@Entity(tableName = "profile")
data class Profile(
    @PrimaryKey(autoGenerate = true)
    override val id: Long = 0L,
    override val name: String,
    override val profilePictureUri: Uri
) : Profile()