package com.wing.tree.reptile.tree.data.mapper

import com.wing.tree.reptile.tree.domain.model.Profile

object ProfileMapper {
    fun Profile.toEntity() = com.wing.tree.reptile.tree.data.entity.Profile(
        name = name,
        imageFilePath = imageFilePath
    )
}