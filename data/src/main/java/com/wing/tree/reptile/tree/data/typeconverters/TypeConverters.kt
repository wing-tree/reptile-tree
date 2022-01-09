package com.wing.tree.reptile.tree.data.typeconverters

import android.net.Uri
import androidx.core.net.toUri
import androidx.room.TypeConverter

class TypeConverters {
    @TypeConverter
    fun uriToString(value: Uri): String = value.toString()

    @TypeConverter
    fun stringToUri(value: String): Uri = value.toUri()
}