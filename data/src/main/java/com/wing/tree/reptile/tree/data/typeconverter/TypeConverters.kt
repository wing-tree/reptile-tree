package com.wing.tree.reptile.tree.data.typeconverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.wing.tree.reptile.tree.domain.model.Diary.Content

class TypeConverters {
    @TypeConverter
    fun imageArrayToJson(value: Array<Content.Image>): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToImageArray(value: String): Array<Content.Image> = Gson().fromJson(
        value,
        Array<Content.Image>::class.java
    )

    @TypeConverter
    fun textArrayToJson(value: Array<Content.Text>): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToTextArray(value: String): Array<Content.Text> = Gson().fromJson(
        value,
        Array<Content.Text>::class.java
    )
}