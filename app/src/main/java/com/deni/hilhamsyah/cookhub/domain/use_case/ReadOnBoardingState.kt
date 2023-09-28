package com.deni.hilhamsyah.cookhub.domain.use_case

import com.deni.hilhamsyah.cookhub.domain.repository.DatastoreRepository
import kotlinx.coroutines.flow.Flow

class ReadOnBoardingState(
    private val datastoreRepository: DatastoreRepository
) {
    operator fun invoke(): Flow<Boolean> {
        return datastoreRepository.readOnboardingState()
    }
}