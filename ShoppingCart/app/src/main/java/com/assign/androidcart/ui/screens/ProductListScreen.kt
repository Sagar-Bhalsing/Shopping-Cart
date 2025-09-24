package com.assign.androidcart.ui.screens


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.assign.androidcart.data.db.CartItemEntity
import com.assign.androidcart.vm.MainViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(viewModel: MainViewModel, onOpenCart: () -> Unit) {
    val state = viewModel.uiState.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Products") },
                actions = {
                    IconButton(onClick = onOpenCart) {
                        Icon(Icons.Default.ShoppingCart, contentDescription = "Cart")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onOpenCart) {
                BadgedBox(
                    badge = {
                        if (state.cartItems.isNotEmpty()) {
                            Badge { Text("${state.cartItems.size}") }
                        }
                    }
                ) {
                    Icon(Icons.Default.ShoppingCart, contentDescription = "Cart")
                }
            }
        }
    ) { padding ->
        if (state.loading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                CircularProgressIndicator()
            }
            return@Scaffold
        }

        state.error?.let { err ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                Text("Error: $err")
            }
            return@Scaffold
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            items(state.products) { p ->
                ProductCard(
                    product = p,
                    cartItems = state.cartItems,
                    onAddToCart = { viewModel.addToCart(p) },
                    onIncrement = { viewModel.incrementQuantity(CartItemEntity(p.id, p.title, p.price, p.image, state.cartItems.find { it.id == p.id }?.quantity ?: 1)) },
                    onDecrement = { viewModel.decrementQuantity(CartItemEntity(p.id, p.title, p.price, p.image, state.cartItems.find { it.id == p.id }?.quantity ?: 1)) }
                )
            }
        }
    }
}

