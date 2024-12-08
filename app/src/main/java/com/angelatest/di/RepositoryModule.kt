package com.angelatest.di

import com.angelatest.data.repository.home.medicineRepository.HomeRepository
import com.angelatest.data.repository.home.medicineRepository.HomeRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    fun provideHomeRepository(homeRepo: HomeRepositoryImp): HomeRepository = homeRepo

}