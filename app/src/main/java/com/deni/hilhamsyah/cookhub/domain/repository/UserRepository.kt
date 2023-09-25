package com.deni.hilhamsyah.cookhub.domain.repository

import com.deni.hilhamsyah.cookhub.domain.model.Uid
import com.deni.hilhamsyah.cookhub.domain.model.User
import com.deni.hilhamsyah.cookhub.domain.model.response.CheckUserNameResponse
import com.deni.hilhamsyah.cookhub.domain.model.response.IsFollowingResponse
import com.deni.hilhamsyah.cookhub.domain.model.response.SuccessResponse
import com.deni.hilhamsyah.cookhub.util.Resource
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUserProfile(): Flow<Resource<User>>

    fun addUserProfile(user: User): Flow<Resource<SuccessResponse>>

    fun updateUserProfile(user: User): Flow<Resource<User>>

    fun getUserProfileById(userId: String): Flow<Resource<User>>

    fun getUserFollowers(userId: String): Flow<Resource<List<Uid>>>

    fun getUserFollowing(userId: String): Flow<Resource<List<Uid>>>

    fun getUserRecipes(userId: String): Flow<Resource<List<Uid>>>

    fun followUser(userId: String): Flow<Resource<SuccessResponse>>

    fun unfollowUser(userId: String): Flow<Resource<SuccessResponse>>

    fun isFollowing(userId: String): Flow<Resource<IsFollowingResponse>>

    fun setUserName(userName: Map<String, String>): Flow<Resource<SuccessResponse>>

    fun isUserNameExists(userName: Map<String, String>): Flow<Resource<CheckUserNameResponse>>
}