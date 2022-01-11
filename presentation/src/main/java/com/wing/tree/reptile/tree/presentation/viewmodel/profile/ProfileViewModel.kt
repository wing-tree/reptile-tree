package com.wing.tree.reptile.tree.presentation.viewmodel.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wing.tree.reptile.tree.domain.model.Diary
import com.wing.tree.reptile.tree.domain.model.Profile
import com.wing.tree.reptile.tree.domain.usecase.Result
import com.wing.tree.reptile.tree.domain.usecase.diary.GetDiaryListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getDiaryListUseCase: GetDiaryListUseCase,
    application: Application
) : AndroidViewModel(application) {
    var profile: Profile? = null

    private val _diaryList = MutableLiveData<List<Diary>>()
    val diaryList: LiveData<List<Diary>>  get() = _diaryList

    fun diaryList(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            getDiaryListUseCase.invoke(GetDiaryListUseCase.Parameter(id)).collectLatest {
                when(it) {
                    is Result.Success -> _diaryList.postValue(it.data)
                    is Result.Error -> Timber.e(it.throwable)
                    is Result.Loading -> {
                    }
                }
            }
        }
    }
}