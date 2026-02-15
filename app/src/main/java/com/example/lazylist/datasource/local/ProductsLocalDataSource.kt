package com.example.lazylist.datasource.local

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.lazylist.db.ProductDataBase
import com.example.lazylist.model.Product

class ProductsLocalDataSource(context : Context) {
    private val dao = ProductDataBase.getInstance(context)
    private val productDao = dao.productDao()

    suspend fun insertProducts(product : Product)  = productDao.insert(product)

     fun getFavProducts() : LiveData <List<Product>> = productDao.getAll()

    suspend fun deleteFavProduct(product : Product) = productDao.delete(product)
}