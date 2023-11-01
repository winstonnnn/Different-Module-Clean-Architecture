package com.fortune.data.api

import com.fortune.domain.model.params.LoginParam
import com.fortune.domain.model.response.User
import retrofit2.http.*

interface ApiService {

    @POST("user/login")
    suspend fun login(@Body loginParam: LoginParam): User

    @GET("user/index")
    suspend fun getUserInfo(): User
}