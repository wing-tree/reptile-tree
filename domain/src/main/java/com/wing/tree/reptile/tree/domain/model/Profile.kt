package com.wing.tree.reptile.tree.domain.model

import android.net.Uri

abstract class Profile {
    abstract val name: String
    abstract val profilePictureUri: Uri

    open val id: Long = 0L

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Profile) return false

        if (name != other.name) return false
        if (profilePictureUri != other.profilePictureUri) return false
        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + profilePictureUri.hashCode()
        result = 31 * result + id.hashCode()
        return result
    }
}