package com.reza.samplelogin.data.repository

import com.reza.appmovies.data.models.ResponseRegister
import com.reza.samplelogin.data.api.ApiService
import com.reza.samplelogin.data.models.BodyRegister
import com.reza.samplelogin.data.repository.source.LoginDataSource
import retrofit2.Response
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val loginRemoteDataSource: LoginDataSource) :
    LoginRepository {
    override suspend fun login(bodyRegister: BodyRegister): Response<ResponseRegister> =
        loginRemoteDataSource.login(bodyRegister)
}