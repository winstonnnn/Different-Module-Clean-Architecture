package com.fortune.domain.use_case

import com.fortune.domain.common.Resource
import com.fortune.domain.model.params.LoginParam
import com.fortune.domain.model.response.User
import com.fortune.domain.repository.ILoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: ILoginRepository
) {
    operator fun invoke(loginParam: LoginParam): Flow<Resource<User>> = flow {
        try {
            val user = repository.login(loginParam)
            emit(Resource.Success(user))
        }catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }

    fun getUserInfo() : Flow<Resource<User>> = flow {
        try {
            val user = repository.getUserInfo()
            emit(Resource.Success(user))
        }catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }
}