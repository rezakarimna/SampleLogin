package com.reza.samplelogin.di

import com.reza.samplelogin.data.api.ApiService
import com.reza.samplelogin.data.repository.LoginRepository
import com.reza.samplelogin.data.repository.LoginRepositoryImpl
import com.reza.samplelogin.data.repository.source.LoginDataSource
import com.reza.samplelogin.data.repository.source.LoginRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideLoginRepository(LoginRemoteDataSource: LoginDataSource): LoginRepository =
        LoginRepositoryImpl(LoginRemoteDataSource)

    @Provides
    @Singleton
    fun provideLoginDataSource(apiService: ApiService): LoginDataSource =
        LoginRemoteDataSource(apiService)

}

