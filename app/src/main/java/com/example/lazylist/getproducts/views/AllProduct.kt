package com.example.lazylist.getproducts.views

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.lazylist.getproducts.viewmodel.GetProductsState
import com.example.lazylist.model.Product


@Composable
fun AllProduct(
    state: GetProductsState,
    insertProductClick: (Product) -> Unit = {},
    insertSuccess: Int
) {

    val context = LocalContext.current

    LaunchedEffect(insertSuccess) {
        if (insertSuccess > 0) {
            Toast.makeText(
                context,
                "Product inserted successfully",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    when (state) {

        is GetProductsState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is GetProductsState.Success -> {
            ListView(
                itemList = state.products,
                onItemClick = insertProductClick
            )
        }

        is GetProductsState.Error -> {
            Toast.makeText(
                context,
                "Error: ${state.message}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
