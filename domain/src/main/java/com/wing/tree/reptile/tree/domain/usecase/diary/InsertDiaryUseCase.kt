package com.wing.tree.reptile.tree.domain.usecase.diary

import com.wing.tree.reptile.tree.domain.model.Diary
import com.wing.tree.reptile.tree.domain.repository.DiaryRepository
import com.wing.tree.reptile.tree.domain.usecase.IOCoroutineDispatcher
import com.wing.tree.reptile.tree.domain.usecase.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class InsertDiaryUseCase @Inject constructor(
    private val diaryRepository: DiaryRepository,
    @IOCoroutineDispatcher coroutineDispatcher: CoroutineDispatcher
) : UseCase <InsertDiaryUseCase.Parameter, Unit>(coroutineDispatcher) {
    override suspend fun execute(parameter: Parameter) {
        diaryRepository.insert(parameter.diary)
    }

    data class Parameter(val diary: Diary)
}