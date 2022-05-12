package com.wayfairproduct.takehome.data

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProductDatabaseTest : TestCase() {
    
    val TAG = "TAG"

    private lateinit var db: ProductDatabase
    private lateinit var dao: ProductDao

    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, ProductDatabase::class.java).build()
        dao = db.productDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun writeAndReadProducts() = runBlocking {

        val testProductOne = Product("product-name", "product-desc", 5.0, "2022-02-02")
        val listOfTestProducts = mutableListOf(testProductOne)

        Log.d(TAG, "writeAndReadProducts: listOfTest ---> $listOfTestProducts")
        dao.insertProducts(listOfTestProducts)

        val lastTwenty = dao.getLastTwentyProducts()
        Log.d(TAG, "writeAndReadProducts: lastTwenty ---> $lastTwenty")

        assertTrue(lastTwenty == listOfTestProducts)

    }
}