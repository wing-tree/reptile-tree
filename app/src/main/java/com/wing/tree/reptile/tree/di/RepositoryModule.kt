package com.wing.tree.reptile.tree.di

import com.wing.tree.reptile.tree.data.repository.ProfileRepositoryImpl
import com.wing.tree.reptile.tree.domain.repository.ProfileRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindsProfileRepository(repository: ProfileRepositoryImpl): ProfileRepository
}