package com.assign.androidcart.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.assign.androidcart.data.db.CartItemEntity
import com.assign.androidcart.vm.MainViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(viewModel: MainViewModel, onBack: () -> Unit) {
    val state = viewModel.uiState.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Cart") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        bottomBar = {
            if (state.cartItems.isNotEmpty()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        "Total: ₹ ${"%.2f".format(viewModel.totalCartValue())}",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Button(onClick = { /* TODO: checkout action */ }) {
                        Text("Checkout")
                    }
                }
            }
        }
    ) { padding ->
        if (state.cartItems.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("Cart is empty")
            }
            return@Scaffold
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            items(state.cartItems) { item ->
                CartItemRow(
                    item = item,
                    onIncrease = { viewModel.incrementQuantity(it) },
                    onDecrease = { viewModel.decrementQuantity(it) }
                )
            }
        }
    }
}

@Composable
fun CartItemRow(
    item: CartItemEntity,
    onIncrease: (CartItemEntity) -> Unit,
    onDecrease: (CartItemEntity) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(modifier = Modifier.weight(1f)) {
                AsyncImage(
                    model = item.image,
                    contentDescription = null,
                    modifier = Modifier
                        .size(72.dp)
                        .clip(RoundedCornerShape(12.dp))
                )
                Spacer(Modifier.width(12.dp))
                Column {
                    Text(item.title, style = MaterialTheme.typography.titleMedium, maxLines = 2)
                    Spacer(Modifier.height(6.dp))
                    Text(
                        "₹ ${"%.2f".format(item.price)}",
                        style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.primary)
                    )
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { onDecrease(item) }) {
                    Icon(
                        Icons.Default.Delete, contentDescription = "Decrease",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                Text("${item.quantity}", style = MaterialTheme.typography.titleMedium)
                IconButton(onClick = { onIncrease(item) }) {
                    Icon(
                        Icons.Default.Add, contentDescription = "Increase",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

