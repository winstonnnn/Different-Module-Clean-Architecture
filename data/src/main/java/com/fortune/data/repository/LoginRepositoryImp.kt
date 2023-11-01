package com.fortune.data.repository

import com.fortune.data.api.ApiService
import com.fortune.domain.model.params.LoginParam
import com.fortune.domain.model.response.User
import com.fortune.domain.repository.ILoginRepository
import javax.inject.Inject

class LoginRepositoryImp @Inject constructor(
    private val api: ApiService
) : ILoginRepository{

    override suspend fun login(loginParam: LoginParam): User {
        return api.login(loginParam)
    }

    override suspend fun getUserInfo(): User {
        return api.getUserInfo()
    }
}