package com.example.lazylist.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity("products")
data class Product  (
    @PrimaryKey(autoGenerate = true)
    val id : Long,
    val title : String? ,
    val price : Double? ,
    val thumbnail : String?,
    val description : String?,
    val brand : String?,
    val category : String?

): Serializable {}