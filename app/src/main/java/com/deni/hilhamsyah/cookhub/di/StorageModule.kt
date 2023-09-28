package com.deni.hilhamsyah.cookhub.di

import com.deni.hilhamsyah.cookhub.data.remote.FirebaseStorageRepositoryImpl
import com.deni.hilhamsyah.cookhub.domain.repository.FirebaseStorageRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StorageModule {

    @Provides
    @Singleton
    fun provideFirebaseStorage() = Firebase.storage

    @Provides
    @Singleton
    fun provideFirebaseStorageRepository(
        firebaseStorage: FirebaseStorage,
        firebaseAuth: FirebaseAuth
    ): FirebaseStorageRepository = FirebaseStorageRepositoryImpl(firebaseStorage, firebaseAuth)
}