package com.example.testtask.di

import com.example.testtask.data.CatalogRepositoryImpl
import com.example.testtask.domain.CatalogRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideCatalogRepository(
        catalogRepositoryImpl: CatalogRepositoryImpl
    ): CatalogRepository = catalogRepositoryImpl
}