package com.wing.tree.reptile.tree.presentation.viewmodel.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wing.tree.reptile.tree.domain.model.Profile
import com.wing.tree.reptile.tree.domain.usecase.map
import com.wing.tree.reptile.tree.domain.usecase.profile.GetProfileListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileListViewModel @Inject constructor(
    private val getProfileListUseCase: GetProfileListUseCase,
    application: Application
) : AndroidViewModel(application) {
    private val _profileList = MutableLiveData<List<Profile>>()
    val profileList: LiveData<List<Profile>> get() = _profileList

    fun getProfileList() {
        viewModelScope.launch(Dispatchers.IO) {
            getProfileListUseCase.invoke(Unit).map {
                _profileList.postValue(it)
            }
        }
    }
}