package com.wayfairproduct.takehome.repository

import androidx.room.withTransaction
import com.wayfairproduct.takehome.api.ProductApi
import com.wayfairproduct.takehome.data.ProductDatabase
import com.wayfairproduct.takehome.utils.networkBoundResource
import kotlinx.coroutines.delay
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val api: ProductApi,
    private val db: ProductDatabase
) {
    private val productDao = db.productDao()

    // CACHE
    fun getProducts() = networkBoundResource(
        query = {
            productDao.getAllProducts()
        },

        fetch = {
            delay(1000)
            api.getProducts()
        },

        saveFetchResult = { news ->
            // all operations or nothing..
            db.withTransaction {
                productDao.deleteAllProducts()
                productDao.insertProducts(news)
            }
        }
    )

}