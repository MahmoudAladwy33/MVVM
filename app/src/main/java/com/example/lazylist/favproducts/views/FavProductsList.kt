package com.example.lazylist.favproducts.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.example.lazylist.getproducts.views.ListItem
import com.example.lazylist.model.Product

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun FavProductsList(modifier: Modifier = Modifier,productList : List<Product> = emptyList(), onItemClick:  (Product) -> Unit) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(top = 50.dp , bottom = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(productList){product ->
            FavProductsListItem(
                product = product,
                onClick = { onItemClick(product) }
            )

        }
    }
    }