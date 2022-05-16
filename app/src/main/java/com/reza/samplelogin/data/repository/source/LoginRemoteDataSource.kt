package com.reza.samplelogin.data.repository.source

import com.reza.appmovies.data.models.ResponseRegister
import com.reza.samplelogin.data.api.ApiService
import com.reza.samplelogin.data.models.BodyRegister
import retrofit2.Response
import javax.inject.Inject

class LoginRemoteDataSource @Inject constructor(private val apiService: ApiService) :
    LoginDataSource {
    override suspend fun login(bodyRegister: BodyRegister): Response<ResponseRegister> =
        apiService.registerUser(bodyRegister)
}