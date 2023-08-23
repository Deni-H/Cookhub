package com.deni.hilhamsyah.cookhub.ui.onboarding_screen

import androidx.lifecycle.ViewModel
import com.deni.hilhamsyah.cookhub.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {
    fun isLoggedIn(): Boolean {
        return authRepository.isLoggedIn()
    }
}