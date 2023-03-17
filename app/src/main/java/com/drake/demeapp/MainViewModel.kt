package com.drake.demeapp

import androidx.lifecycle.MutableLiveData
import com.drake.demeapp.core.BaseViewModel

class MainViewModel : BaseViewModel() {
    companion object {
        private const val TIMEOUT = 2 * 60 * 1000
        private const val MIN = 60* 1000
    }

    val time = MutableLiveData(TIMEOUT.toLong())
    val onClick = MutableLiveData(false);
    val isReset = MutableLiveData(false)
    val dismissBottomSheet = MutableLiveData(false)

    fun getTime(): Long {
        return time.value ?: TIMEOUT.toLong();
    }

    fun setTime(value: Long) {
        if (value < 0){
            toastMessage.postValue("Invalid")
        }

        time.postValue(value * 1000)
    }

    fun onClick() {
        onClick.postValue(true)
    }
}