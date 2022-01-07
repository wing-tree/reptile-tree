package com.wing.tree.reptile.tree.domain.model

abstract class Profile {
    abstract val id: Long
    abstract val name: String
    abstract val photo: String

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Profile

        if (id != other.id) return false
        if (name != other.name) return false
        if (photo != other.photo) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + photo.hashCode()
        return result
    }
}