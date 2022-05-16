package com.reza.samplelogin.data.repository

import com.reza.appmovies.data.models.ResponseRegister
import com.reza.samplelogin.data.models.BodyRegister
import retrofit2.Response

interface LoginRepository {
    suspend fun login(bodyRegister: BodyRegister): Response<ResponseRegister>
}