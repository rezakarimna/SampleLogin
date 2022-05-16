package com.reza.samplelogin.di

import com.reza.samplelogin.data.models.BodyRegister
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BodyRegisterModule {

    @Provides
    @Singleton
    fun provideBodyRegister() = BodyRegister()
}