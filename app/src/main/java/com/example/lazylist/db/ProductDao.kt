package com.example.lazylist.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lazylist.model.Product

@Dao
interface ProductDao {
    @Query("SELECT * FROM products")
     fun getAll(): LiveData< List <Product>>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(products: Product)

    @Delete
    suspend fun delete(products: Product)
}