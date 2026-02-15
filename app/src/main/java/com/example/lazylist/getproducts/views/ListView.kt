package com.example.lazylist.getproducts.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.lazylist.model.Product

@Composable
fun ListView(modifier: Modifier = Modifier, itemList : List<Product> = emptyList(), onItemClick:  (Product) -> Unit){
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(top = 50.dp , bottom = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(itemList){product ->
            ListItem(
                product = product,
                onClick = { onItemClick(product) }
            )

        }
    }
}