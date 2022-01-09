package com.wing.tree.reptile.tree.domain.usecase.profile

import com.wing.tree.reptile.tree.domain.model.Profile
import com.wing.tree.reptile.tree.domain.repository.ProfileRepository
import com.wing.tree.reptile.tree.domain.usecase.IOCoroutineDispatcher
import com.wing.tree.reptile.tree.domain.usecase.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class InsertProfileUseCase @Inject constructor(
    private val profileRepository: ProfileRepository,
    @IOCoroutineDispatcher coroutineDispatcher: CoroutineDispatcher
) : UseCase<InsertProfileUseCase.Parameter, Unit>(coroutineDispatcher) {
    override suspend fun execute(parameter: Parameter) {
        profileRepository.insert(parameter.profile)
    }

    data class Parameter(
        val profile: Profile
    )
}