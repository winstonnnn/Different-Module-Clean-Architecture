package com.fortune.domain.repository

import com.fortune.domain.model.params.LoginParam
import com.fortune.domain.model.response.User

interface ILoginRepository {
    suspend fun login(loginParam: LoginParam): User
    suspend fun getUserInfo(): User
}