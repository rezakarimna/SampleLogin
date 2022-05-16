package com.reza.samplelogin.data.repository.source

import com.reza.appmovies.data.models.ResponseRegister
import com.reza.samplelogin.data.models.BodyRegister
import retrofit2.Response

interface LoginDataSource {
    suspend fun login(bodyRegister: BodyRegister): Response<ResponseRegister>
}