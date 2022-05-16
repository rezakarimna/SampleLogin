package com.reza.samplelogin.data.api

import com.reza.appmovies.data.models.ResponseRegister
import com.reza.samplelogin.data.models.BodyRegister
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @POST("register")
    suspend fun registerUser(@Body body: BodyRegister): Response<ResponseRegister>
}