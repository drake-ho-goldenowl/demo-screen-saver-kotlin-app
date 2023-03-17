package com.drake.demeapp.core

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


abstract class BaseViewModel : ViewModel() {
    val toastMessage = MutableLiveData("")
}