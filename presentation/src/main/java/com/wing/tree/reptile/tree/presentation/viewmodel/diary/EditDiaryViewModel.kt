package com.wing.tree.reptile.tree.presentation.viewmodel.diary

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.wing.tree.reptile.tree.domain.model.Diary
import com.wing.tree.reptile.tree.domain.usecase.diary.InsertDiaryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class EditDiaryViewModel @Inject constructor(
    private val insertDiaryUseCase: InsertDiaryUseCase,
    application: Application
) : AndroidViewModel(application) {
    fun insertDiary(diary: Diary) {
        viewModelScope.launch(Dispatchers.IO) {
            insertDiaryUseCase.invoke(InsertDiaryUseCase.Parameter(diary))
        }
    }
}