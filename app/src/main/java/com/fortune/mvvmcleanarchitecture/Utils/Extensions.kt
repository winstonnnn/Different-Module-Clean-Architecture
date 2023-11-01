package com.fortune.mvvmcleanarchitecture.Utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

fun <T: Any> LifecycleOwner.observer(data: LiveData<T?>, function: (T) -> Unit) {
    data.observe(this) {
        if(it?.javaClass != null) function(it)
    }
}

fun <T: Any>LifecycleOwner.collect(data: StateFlow<T?>, function: (T) -> Unit) {
    this.lifecycleScope.launch {
        data.collectLatest{
            if (it != null) {
                function(it)
            }
        }
    }
}