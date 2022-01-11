package com.wing.tree.reptile.tree.domain.model

abstract class Diary {
    abstract val profileId: Long
    abstract val title: String
    abstract val textArray: Array<Content.Text>
    abstract val imageArray: Array<Content.Image>

    open val id: Long = 0L

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Diary) return false

        if (profileId != other.profileId) return false
        if (title != other.title) return false
        if (!textArray.contentEquals(other.textArray)) return false
        if (!imageArray.contentEquals(other.imageArray)) return false
        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        var result = profileId.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + textArray.contentHashCode()
        result = 31 * result + imageArray.contentHashCode()
        result = 31 * result + id.hashCode()
        return result
    }

    sealed class Content {
        abstract val position: Int

        data class Text(
            override val position: Int,
            val value: String
        ) : Content()

        data class Image(
            override val position: Int,
            val filePath: String
        ) : Content()
    }
}