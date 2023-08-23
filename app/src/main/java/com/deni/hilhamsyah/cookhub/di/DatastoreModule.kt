package com.deni.hilhamsyah.cookhub.di

import android.content.Context
import com.deni.hilhamsyah.cookhub.data.local.DatastoreRepositoryImpl
import com.deni.hilhamsyah.cookhub.domain.repository.DatastoreRepository
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
}