package com.wing.tree.reptile.tree.data.mapper

import com.wing.tree.reptile.tree.domain.model.Diary

object DiaryMapper {
    fun Diary.toEntity() = com.wing.tree.reptile.tree.data.entity.Diary(
        profileId = profileId,
        title = title,
        textArray = textArray.clone(),
        imageArray = imageArray.clone()
    )
}