package com.wayfairproduct.takehome.viewmodel

import androidx.lifecycle.*
import com.wayfairproduct.takehome.api.ProductApi
import com.wayfairproduct.takehome.data.Product
import com.wayfairproduct.takehome.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    repository: ProductRepository
) : ViewModel() {

    val products = repository.getProducts().asLiveData()

//    private val productsLiveData = MutableLiveData<List<Product>>()
//    val products: LiveData<List<Product>> = productsLiveData
//
//    init {
//
//        viewModelScope.launch {
//            val product = api.getProducts()
//            delay(500)
//            productsLiveData.value = product
//        }
//    }

}