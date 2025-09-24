package com.assign.androidcart.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.assign.androidcart.ui.screens.CartScreen
import com.assign.androidcart.ui.screens.ProductListScreen
import com.assign.androidcart.vm.MainViewModel


@Composable
fun AppNav(vm: MainViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "products") {
        composable("products") {
            ProductListScreen(
                viewModel = vm,
                onOpenCart = { navController.navigate("cart") }
            )
        }
        composable("cart") {
            CartScreen(
                viewModel = vm,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
