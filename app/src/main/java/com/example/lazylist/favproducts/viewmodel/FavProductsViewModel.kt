package com.example.lazylist.favproducts.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.lazylist.datasource.Repo
import com.example.lazylist.model.Product
import kotlinx.coroutines.launch

class FavProductsViewModel(context : Context) : ViewModel() {


    private val repo : Repo = Repo(context )

    fun getFavProducts(): LiveData<List<Product>> = repo.getFavProducts()


    fun deleteFavProduct(product: Product) {
        viewModelScope.launch {
            repo.deleteProducts(product)
        }
    }
}

class FavProductsViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FavProductsViewModel(context ) as T
    }
}


