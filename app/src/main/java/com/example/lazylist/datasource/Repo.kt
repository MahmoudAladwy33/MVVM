package com.example.lazylist.datasource

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.lazylist.datasource.local.ProductsLocalDataSource
import com.example.lazylist.datasource.remote.ProductsRemoteDataSource
import com.example.lazylist.model.Product

class Repo(context : Context ) {

    private val remoteDataSource = ProductsRemoteDataSource()
    private val localDataSource = ProductsLocalDataSource(context)

    suspend fun getProducts(): List<Product> = remoteDataSource.getProducts()

    suspend fun insertProduct(product: Product) = localDataSource.insertProducts(product)

     fun getFavProducts() : LiveData <List<Product>> = localDataSource.getFavProducts()

        suspend fun deleteProducts(product : Product) = localDataSource.deleteFavProduct(product)


    }