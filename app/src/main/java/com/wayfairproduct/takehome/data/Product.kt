package com.wayfairproduct.takehome.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
    @PrimaryKey
    val name: String,
    val tagline: String,
    val rating: Double,
    val date: String
)