package com.wing.tree.reptile.tree.presentation.viewmodel.profile

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wing.tree.reptile.tree.domain.model.Profile
import com.wing.tree.reptile.tree.domain.usecase.profile.InsertProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateProfileViewModel @Inject constructor(
    private val insertProfileUseCase: InsertProfileUseCase,
    application: Application
) : AndroidViewModel(application) {
    private val _imageFileUri = MutableLiveData<Uri>()
    val imageFileUri: LiveData<Uri> get() = _imageFileUri

    fun insertProfile(profile: Profile) {
        viewModelScope.launch(Dispatchers.IO) {
            insertProfileUseCase.invoke(InsertProfileUseCase.Parameter(profile))
        }
    }

    fun setImageFileUri(value: Uri) {
        _imageFileUri.value = value
    }
}