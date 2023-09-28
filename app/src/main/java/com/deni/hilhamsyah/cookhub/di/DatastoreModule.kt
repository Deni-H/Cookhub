package com.deni.hilhamsyah.cookhub.di

import android.content.Context
import com.deni.hilhamsyah.cookhub.data.local.DatastoreRepositoryImpl
import com.deni.hilhamsyah.cookhub.domain.repository.DatastoreRepository
import com.deni.hilhamsyah.cookhub.domain.use_case.ReadOnBoardingState
import com.deni.hilhamsyah.cookhub.domain.use_case.SaveOnBoardingState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatastoreModule {

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DatastoreRepository {
        return DatastoreRepositoryImpl(context)
    }

    @Provides
    @Singleton
    fun provideSaveOnBoardingState(
        datastoreRepository: DatastoreRepository
    ): SaveOnBoardingState {
        return SaveOnBoardingState(datastoreRepository)
    }

    @Provides
    @Singleton
    fun provideReadOnBoardingState(
        datastoreRepository: DatastoreRepository
    ): ReadOnBoardingState {
        return ReadOnBoardingState(datastoreRepository)
    }
}