package com.fortune.mvvmcleanarchitecture.ui

import android.content.ComponentCallbacks2
import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import com.fortune.mvvmcleanarchitecture.BR
import kotlin.reflect.KClass

abstract class BaseActivity<VB: ViewDataBinding, VM: ViewModel>(
    private val bindingInflater: (inflater: LayoutInflater) -> VB,
    model: KClass<out VM>
) : AppCompatActivity(), ComponentCallbacks2 {

    val viewModel by ViewModelLazy(model, { viewModelStore }, { defaultViewModelProviderFactory })
    private var _binding: VB? = null
    val binding: VB get() = _binding.let {
        when(it){
            null -> bindingInflater(layoutInflater)
            else -> it
        }
    }

    open fun VB.initialize() {}
    open fun VM.initObserver() {}
    open fun VM.initCall() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = bindingInflater(layoutInflater)
        viewModel.initCall()

        if (_binding == null)
            throw Exception("Binding is null")

        binding.setVariable(BR.model, viewModel)
        binding.initialize()
        viewModel.initObserver()
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}