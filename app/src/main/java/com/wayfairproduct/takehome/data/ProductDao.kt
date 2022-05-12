package com.wayfairproduct.takehome.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query("SELECT * FROM products")
    fun getAllProducts() : Flow<List<Product>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(products: List<Product>)

    @Query("DELETE FROM products")
    suspend fun deleteAllProducts()

    // UNIT-TEST (D/B)
    @Query("SELECT * FROM products ORDER BY name DESC LIMIT 20")
    suspend fun getLastTwentyProducts(): List<Product>
}