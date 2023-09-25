package com.deni.hilhamsyah.cookhub.ui.home_screen

import androidx.lifecycle.ViewModel
import com.deni.hilhamsyah.cookhub.domain.repository.UserRepository
import com.deni.hilhamsyah.cookhub.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {

    private val _userProfileState = Channel<UserProfileState>()
    val userProfileState = _userProfileState.receiveAsFlow()

    suspend fun getUserProfile() {
        userRepository.getUserProfile().collect { result ->
            when(result) {
                is Resource.Loading -> {
                    _userProfileState.send(UserProfileState(isLoading = true))
                }
                is Resource.Success -> {
                    _userProfileState.send(UserProfileState(user = result.data))
                }
                is Resource.Error -> {
                    _userProfileState.send(UserProfileState(fail = result.e?.message))
                }
            }
        }
    }
}