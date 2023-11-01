package com.fortune.mvvmcleanarchitecture.ui.login

import android.view.View.OnClickListener
import android.widget.Toast
import androidx.lifecycle.Observer
import com.fortune.domain.model.params.LoginParam
import com.fortune.domain.model.response.User
import com.fortune.mvvmcleanarchitecture.Utils.collect
import com.fortune.mvvmcleanarchitecture.Utils.observer
import com.fortune.mvvmcleanarchitecture.databinding.ActivityLoginBinding
import com.fortune.mvvmcleanarchitecture.ui.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel> (
    ActivityLoginBinding::inflate,
    LoginViewModel::class
) {

    override fun LoginViewModel.initObserver() {
        collect(sampleStateFlow, ::loginResponse)
        observer(userResponse, ::loginResponse)
        errorString.observe(this@LoginActivity, errorResponse)
    }

    override fun ActivityLoginBinding.initialize() {
        button.setOnClickListener(buttonClick())
    }

    private var errorResponse = Observer<String> {
        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
    }

    private fun loginResponse(user: User) {
        Toast.makeText(this, "Success: ${user.uid}", Toast.LENGTH_SHORT).show()
    }

    private fun buttonClick() = OnClickListener {
        val loginParam = LoginParam(
            "09057388868",
            binding.edtPassword.text.toString()
        )

        viewModel.login(loginParam)
    }
}