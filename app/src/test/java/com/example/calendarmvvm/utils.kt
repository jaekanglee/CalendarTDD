package com.example.calendarmvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer


class TestObserver<T> : Observer<T> {

    val observedValues = MutableLiveData<T?>()

    override fun onChanged(value: T?) {
        observedValues.value= value
    }
}

fun <T> LiveData<T>.testObserver() = TestObserver<T>().also {
    observeForever(it)
}