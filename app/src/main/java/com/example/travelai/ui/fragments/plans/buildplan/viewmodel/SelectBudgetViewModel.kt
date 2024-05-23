package com.example.travelai.ui.fragments.plans.buildplan.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SelectBudgetViewModel : ViewModel() {

    private val _total = MutableLiveData(0)

    var total: LiveData<Int> = _total


    fun increase() {
        val t = _total.value ?: 0
        _total.postValue(t + 100)
    }

    fun decrease() {
        val t = total.value ?: 0
        _total.postValue(if (t - 100 < 0) 0 else (t - 100))
    }
}