package com.example.lazylist.datasource.remote

import com.example.lazylist.model.Product
import com.example.lazylist.network.RetrofitHelper

class ProductsRemoteDataSource {

    private val retrofitService = RetrofitHelper.getProductsService

    suspend fun getProducts(): List<Product> = retrofitService.getProducts().products
}