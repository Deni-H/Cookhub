package com.deni.hilhamsyah.cookhub.domain.repository

import com.deni.hilhamsyah.cookhub.domain.model.Uid
import com.deni.hilhamsyah.cookhub.domain.model.User
import com.deni.hilhamsyah.cookhub.domain.model.response.CheckUserNameResponse
import com.deni.hilhamsyah.cookhub.domain.model.response.IsFollowingResponse
import com.deni.hilhamsyah.cookhub.domain.model.response.SuccessResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserApiRepository {

    @GET("api/v1/users/user/")
    suspend fun getUserProfile(): Response<User>

    @POST("api/v1/users/user/")
    suspend fun addUserProfile(@Body user: User): Response<SuccessResponse>

    @PUT("/api/v1/users/user/")
    suspend fun updateUserProfile(@Body user: User): Response<User>

    @GET("api/v1/users/user/{userId}")
    suspend fun getUserProfileById(@Path("userId") userId: String): Response<User>

    @GET("api/v1/users/user/{userId}/followers")
    suspend fun getUserFollowers(@Path("userId") userId: String): Response<List<Uid>>

    @GET("api/v1/users/user/{userId}/following")
    suspend fun getUserFollowing(@Path("userId") userId: String): Response<List<Uid>>

    @GET("api/v1/users/user/{userId}/recipes")
    suspend fun getUserRecipes(@Path("userId") userId: String): Response<List<Uid>>

    @POST("api/v1/users/user/follow/{userId}/")
    suspend fun followUser(@Path("userId") userId: String): Response<SuccessResponse>

    @POST("api/v1/users/user/unfollow/{userId}/")
    suspend fun unfollowUser(@Path("userId") userId: String): Response<SuccessResponse>

    @GET("api/v1/users/user/following/{userId}/")
    suspend fun isFollowing(@Path("userId") userId: String): Response<IsFollowingResponse>

    @POST("api/v1/users/user/username/")
    suspend fun setUserName(@Body userName: Map<String, String>): Response<SuccessResponse>

    @POST("api/v1/users/username/")
    suspend fun isUserNameExists(@Body userName: Map<String, String>): Response<CheckUserNameResponse>
}
