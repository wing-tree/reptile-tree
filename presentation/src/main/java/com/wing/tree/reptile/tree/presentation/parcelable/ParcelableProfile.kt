package com.wing.tree.reptile.tree.presentation.parcelable

import android.os.Parcelable
import com.wing.tree.reptile.tree.domain.model.Profile
import kotlinx.parcelize.Parcelize

@Parcelize
data class ParcelableProfile(
    override val name: String,
    override val photo: String
) : Profile(), Parcelable {
    companion object {
        fun from(profile: Profile) = ParcelableProfile(
            name = profile.name,
            photo = profile.photo
        )
    }
}