package com.deni.hilhamsyah.cookhub.di

import com.deni.hilhamsyah.cookhub.BuildConfig
import com.deni.hilhamsyah.cookhub.data.remote.UserRepositoryImpl
import com.deni.hilhamsyah.cookhub.data.remote.auth.RequestInterceptor
import com.deni.hilhamsyah.cookhub.domain.repository.AuthRepository
import com.deni.hilhamsyah.cookhub.domain.repository.UserApiRepository
import com.deni.hilhamsyah.cookhub.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideRequestInterceptor(
        authRepository: AuthRepository
    ) = RequestInterceptor(authRepository)

    @Provides
    @Singleton
    fun provideOkHttpClient(
        interceptor: RequestInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideUserApiRepository(
        retrofit: Retrofit
    ): UserApiRepository = retrofit.create(UserApiRepository::class.java)

    @Provides
    @Singleton
    fun provideUserRepository(
        userApiRepository: UserApiRepository
    ): UserRepository = UserRepositoryImpl(userApiRepository)
}