package com.example.lazylist.getproducts.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.lazylist.datasource.Repo
import com.example.lazylist.model.Product
import kotlinx.coroutines.launch

class GetProductsViewModel (context: Context): ViewModel() {
    var state by mutableStateOf<GetProductsState>(GetProductsState.Loading)
        private set

    var insertProductSuccess by mutableStateOf(0)
        private set

    private val repo : Repo = Repo(context)

    init {
        getProducts()
}



     fun getProducts(){

       viewModelScope.launch {

           try {
              state = GetProductsState.Loading
               state = GetProductsState.Success( repo.getProducts())
           } catch (e: Exception) {
               state = GetProductsState.Error(e.message ?: "Unknown error")
           }
       }

    }

    fun insertProduct(product: Product) {

        viewModelScope.launch {

               repo.insertProduct(product)
                insertProductSuccess ++

        }
    }

}

class GetProductsViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

            return GetProductsViewModel(context) as T

    }
}



sealed class GetProductsState {
    data class Success(val products: List<Product>) : GetProductsState()
    data class Error(val message: String) : GetProductsState()
    object Loading : GetProductsState()
}




