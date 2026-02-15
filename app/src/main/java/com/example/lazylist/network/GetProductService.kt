package com.example.lazylist.network

import com.example.lazylist.model.Product
import retrofit2.http.GET

interface GetProductService {

@GET("products")
   suspend fun getProducts():ProductsResponse
}

data class ProductsResponse(
    val products: List<Product>
)



