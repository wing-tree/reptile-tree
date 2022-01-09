package com.wing.tree.reptile.tree.domain.model

import android.net.Uri

abstract class Diary {
    abstract val title: String
    abstract val contentList: List<String>
    abstract val pictureFileUriList: List<Uri>
    abstract val positionList: List<Int>

    object Position {
        const val TEXT = 0
        const val PICTURE = 1
    }
}