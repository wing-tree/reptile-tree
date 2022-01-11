package com.wing.tree.reptile.tree.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wing.tree.reptile.tree.domain.model.Diary

@Entity(tableName = "diary")
data class Diary(
    @PrimaryKey(autoGenerate = true)
    override val id: Long = 0L,
    override val profileId: Long,
    override val title: String,
    @ColumnInfo(name = "text_array")
    override val textArray: Array<Content.Text>,
    @ColumnInfo(name = "image_array")
    override val imageArray: Array<Content.Image>
) : Diary() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is com.wing.tree.reptile.tree.data.entity.Diary) return false

        if (id != other.id) return false
        if (title != other.title) return false
        if (!textArray.contentEquals(other.textArray)) return false
        if (!imageArray.contentEquals(other.imageArray)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + textArray.contentHashCode()
        result = 31 * result + imageArray.contentHashCode()
        return result
    }
}