package com.wing.tree.reptile.tree.domain.usecase.diary

import com.wing.tree.reptile.tree.domain.model.Diary
import com.wing.tree.reptile.tree.domain.repository.DiaryRepository
import com.wing.tree.reptile.tree.domain.usecase.FlowUseCase
import com.wing.tree.reptile.tree.domain.usecase.IOCoroutineDispatcher
import com.wing.tree.reptile.tree.domain.usecase.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetDiaryListUseCase @Inject constructor(
    private val diaryRepository: DiaryRepository,
    @IOCoroutineDispatcher coroutineDispatcher: CoroutineDispatcher
) : FlowUseCase<GetDiaryListUseCase.Parameter, List<Diary>>(coroutineDispatcher) {
    override fun execute(parameter: Parameter) = diaryRepository.diaryList(parameter.id)
        .map { Result.Success(it) }
        .catch { Result.Error(it) }

    data class Parameter(val id: Long)
}