package com.wayfairproduct.takehome.di

import android.app.Application
import androidx.room.Room
import com.wayfairproduct.takehome.api.ProductApi
import com.wayfairproduct.takehome.data.ProductDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // MODULE #1: retrofit-object
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(ProductApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    // MODULE #2: product-object
    @Provides
    @Singleton
    fun provideProductApi(retrofit: Retrofit): ProductApi =
        retrofit.create(ProductApi::class.java)


    // MODULE #3: d/b instance
    @Provides
    @Singleton
    fun provideDatabase(app: Application): ProductDatabase =
        Room.databaseBuilder(app, ProductDatabase::class.java, "product database")
            .build()

}