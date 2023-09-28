package com.deni.hilhamsyah.cookhub.ui.onboarding_screen

import androidx.lifecycle.ViewModel
import com.deni.hilhamsyah.cookhub.domain.repository.DatastoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val dataStoreRepository: DatastoreRepository,
) : ViewModel() {

    fun readOnBoardingState(): Boolean {
        return runBlocking(Dispatchers.IO) {
            dataStoreRepository.readOnboardingState().first()
        }
    }
}