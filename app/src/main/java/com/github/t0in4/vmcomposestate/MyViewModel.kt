package com.github.t0in4.vmcomposestate

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue

class MyViewModel : ViewModel() {
    var customerName by mutableStateOf("")

    fun setName(name: String) {
        customerName = name
    }
}