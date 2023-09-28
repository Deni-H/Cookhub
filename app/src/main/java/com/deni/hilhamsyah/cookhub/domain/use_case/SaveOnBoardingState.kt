package com.deni.hilhamsyah.cookhub.domain.use_case

import com.deni.hilhamsyah.cookhub.domain.repository.DatastoreRepository

class SaveOnBoardingState(
    private val dataStoreRepository: DatastoreRepository
) {
    suspend operator fun invoke(isComplete: Boolean) {
        dataStoreRepository.saveOnboardingState(isComplete)
    }
}