package com.fortune.mvvmcleanarchitecture.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fortune.domain.common.Resource
import com.fortune.domain.model.params.LoginParam
import com.fortune.domain.model.response.User
import com.fortune.domain.use_case.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
): ViewModel(){

    private val _sampleStateFlow: MutableStateFlow<User?>  = MutableStateFlow(null)
    val sampleStateFlow: StateFlow<User?> = _sampleStateFlow

    private val _userResponse = MutableLiveData<User?>()
    val userResponse: LiveData<User?> = _userResponse
    private val _userInfoResponse = MutableLiveData<User?>()
    val userInfoResponse: LiveData<User?> = _userInfoResponse
    private val _errorString: MutableLiveData<String> = MutableLiveData(null)
    val errorString: LiveData<String> = _errorString

    fun login(loginParam: LoginParam) {
        loginUseCase.invoke(loginParam).onEach {
            when(it) {
                is Resource.Success -> {
                    _sampleStateFlow.value = it.data
                    _userResponse.value = it.data
                }
                is Resource.Error   -> {
                    _errorString.value = it.message ?: "Error occurred"
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getUserInfo() {
        loginUseCase.getUserInfo().onEach {
            when(it) {
                is Resource.Success -> {
                    _sampleStateFlow.value = it.data
                    _userInfoResponse.value = it.data
                }
                is Resource.Error   -> {
                    _errorString.value = it.message ?: "Error occurred"
                }
            }
        }.launchIn(viewModelScope)
    }


}