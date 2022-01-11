package com.wing.tree.reptile.tree.di

import com.wing.tree.reptile.tree.data.datasource.local.DiaryDataSource
import com.wing.tree.reptile.tree.data.datasource.local.DiaryDataSourceImpl
import com.wing.tree.reptile.tree.data.datasource.local.ProfileDataSource
import com.wing.tree.reptile.tree.data.datasource.local.ProfileDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindsProfileDataSource(dataSource: ProfileDataSourceImpl): ProfileDataSource

    @Binds
    @Singleton
    abstract fun bindsDiaryDataSource(dataSource: DiaryDataSourceImpl): DiaryDataSource
}