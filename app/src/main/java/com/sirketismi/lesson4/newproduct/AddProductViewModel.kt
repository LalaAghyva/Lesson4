package com.sirketismi.lesson4.newproduct

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sirketismi.lesson4.model.Product

class AddProductViewModel : ViewModel() {
    val code = MutableLiveData<String>()
    val name = MutableLiveData<String>()
    val description = MutableLiveData<String>()

    val errorDescription = MutableLiveData<String>()

    val newProductCallback = MutableLiveData<Boolean>()

    fun createNewProduct() : Product {
        return Product(0, code.value ?: "", description.value ?: "")
    }

    fun onNewProductInserted() {
        if(code.value.isNullOrEmpty() || name.value.isNullOrEmpty() || description.value.isNullOrEmpty()) {
            errorDescription.postValue("Butun melumatlari doldurun")
            return
        }
        newProductCallback.postValue(true)
    }

}

