package com.wing.tree.reptile.tree.presentation.viewmodel.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.wing.tree.reptile.tree.domain.model.Profile
import com.wing.tree.reptile.tree.domain.usecase.profile.InsertProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileCreationViewModel @Inject constructor(
    private val insertProfileUseCase: InsertProfileUseCase,
    application: Application
) : AndroidViewModel(application) {
    fun insertProfile(profile: Profile) {
        viewModelScope.launch(Dispatchers.IO) {
            insertProfileUseCase.invoke(InsertProfileUseCase.Parameter(profile))
        }
    }
}