package com.wing.tree.reptile.tree.presentation.viewmodel.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wing.tree.reptile.tree.domain.model.Profile
import com.wing.tree.reptile.tree.domain.usecase.profile.InsertProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class CreateProfileViewModel @Inject constructor(
    private val insertProfileUseCase: InsertProfileUseCase,
    application: Application
) : AndroidViewModel(application) {
    private val _profilePictureFile = MutableLiveData<File>()
    val profilePictureFile: LiveData<File>
        get() = _profilePictureFile

    fun insertProfile(profile: Profile) {
        viewModelScope.launch(Dispatchers.IO) {
            insertProfileUseCase.invoke(InsertProfileUseCase.Parameter(profile))
        }
    }

    fun setProfilePictureFile(value: File) {
        _profilePictureFile.value = value
    }
}