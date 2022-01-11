package com.wing.tree.reptile.tree.domain.model

abstract class Profile {
    abstract val name: String
    abstract val imageFilePath: String

    open val id: Long = 0L

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Profile) return false

        if (name != other.name) return false
        if (imageFilePath != other.imageFilePath) return false
        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + imageFilePath.hashCode()
        result = 31 * result + id.hashCode()
        return result
    }
}