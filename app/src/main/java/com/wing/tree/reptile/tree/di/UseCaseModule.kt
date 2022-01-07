package com.wing.tree.reptile.tree.di

import com.wing.tree.reptile.tree.domain.repository.ProfileRepository
import com.wing.tree.reptile.tree.domain.usecase.IOCoroutineDispatcher
import com.wing.tree.reptile.tree.domain.usecase.profile.GetProfileListUseCase
import com.wing.tree.reptile.tree.domain.usecase.profile.InsertProfileUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher

@InstallIn(ViewModelComponent::class)
@Module
internal object UseCaseModule {
    @Provides
    @ViewModelScoped
    fun providesGetProfileListUseCase(
        profileRepository: ProfileRepository,
        @IOCoroutineDispatcher coroutineDispatcher: CoroutineDispatcher
    ): GetProfileListUseCase {
        return GetProfileListUseCase(profileRepository, coroutineDispatcher)
    }

    @Provides
    @ViewModelScoped
    fun providesInsertProfileUseCase(
        profileRepository: ProfileRepository,
        @IOCoroutineDispatcher coroutineDispatcher: CoroutineDispatcher
    ): InsertProfileUseCase {
        return InsertProfileUseCase(profileRepository, coroutineDispatcher)
    }
}