package com.deni.hilhamsyah.cookhub.domain.repository

import kotlinx.coroutines.flow.Flow

interface DatastoreRepository {
    suspend fun saveOnboardingState(isComplete: Boolean)
    fun readOnboardingState(): Flow<Boolean>
}