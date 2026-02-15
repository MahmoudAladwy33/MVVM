package com.example.lazylist.getproducts.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lazylist.datasource.Repo
import com.example.lazylist.favproducts.viewmodel.FavProductsViewModel
import com.example.lazylist.favproducts.viewmodel.FavProductsViewModelFactory
import com.example.lazylist.favproducts.views.FavProductsList
import com.example.lazylist.getproducts.viewmodel.GetProductsViewModel
import com.example.lazylist.getproducts.viewmodel.GetProductsViewModelFactory
import com.example.lazylist.ui.theme.LazyListTheme


class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        setContent {
            LazyListTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyApp(
                        modifier = Modifier
                    )
                }
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier ) {
    val con = rememberNavController()
    val context = LocalContext.current

    NavHost(
        con,
        startDestination = "MainApp",
    ) {
            composable("all_product") {
                val vm: GetProductsViewModel = viewModel(
                    factory = GetProductsViewModelFactory(context)
                )
                val  state = vm.state
                AllProduct(
                   state = state,
                    insertProductClick = { product ->
                        vm.insertProduct(product)
                    },
                    insertSuccess = vm.insertProductSuccess
                )
            }
            composable("fav_product") {
                val vm : FavProductsViewModel = viewModel(
                    factory = FavProductsViewModelFactory(context)
                )
                val products by vm.getFavProducts().observeAsState(emptyList())
                FavProductsList(
                    productList = products,
                    onItemClick = { product ->
                        vm.deleteFavProduct(product)
                    }
                    )

            }
        composable("MainApp") {
            MainApp(con= con)
        }

    }

}





