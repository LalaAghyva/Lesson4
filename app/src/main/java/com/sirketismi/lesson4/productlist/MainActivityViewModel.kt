package com.sirketismi.lesson4.productlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel(){
    var addProdyctObserver = MutableLiveData<Boolean>()
    fun onAddNewProduct() {
        addProdyctObserver.postValue(true)
    }
}