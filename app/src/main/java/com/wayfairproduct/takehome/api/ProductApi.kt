package com.wayfairproduct.takehome.api

import com.wayfairproduct.takehome.data.Product
import retrofit2.http.GET

interface ProductApi {

    companion object {
        const val BASE_URL = "https://api.wayfair.io/"
    }

    @GET("interview-sandbox/android/json-to-list/products.v1.json")
    suspend fun getProducts(): List<Product>

}