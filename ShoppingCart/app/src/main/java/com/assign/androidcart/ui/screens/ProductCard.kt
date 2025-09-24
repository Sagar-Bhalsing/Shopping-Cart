package com.assign.androidcart.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.assign.androidcart.data.db.CartItemEntity
import com.assign.androidcart.data.model.ProductDto

@Composable
fun ProductCard(
    product: ProductDto, // Changed to pass ProductDto for ID check
    cartItems: List<CartItemEntity>, // Pass cartItems to check if product is in cart
    onAddToCart: () -> Unit,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit
) {
    val cartItem = cartItems.find { it.id == product.id }
    val isInCart = cartItem != null
    val quantity = cartItem?.quantity ?: 0

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(modifier = Modifier.weight(1f)) {
                AsyncImage(
                    model = product.image,
                    contentDescription = null,
                    modifier = Modifier
                        .size(72.dp)
                        .clip(RoundedCornerShape(12.dp)),
                )
                Spacer(Modifier.width(12.dp))
                Column {
                    Text(
                        product.title,
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 2
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        "₹ ${"%.2f".format(product.price)}",
                        style = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.primary)
                    )
                }
            }
            Spacer(Modifier.width(8.dp))
            if (isInCart) {
                // Show - and + buttons with quantity
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = onDecrement,
                        modifier = Modifier.size(36.dp)
                    ) {
                        Icon(
                            Icons.Default.Delete,
                            contentDescription = "Decrease quantity",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    Text(
                        text = "$quantity",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    IconButton(
                        onClick = onIncrement,
                        modifier = Modifier.size(36.dp)
                    ) {
                        Icon(
                            Icons.Default.Add,
                            contentDescription = "Increase quantity",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            } else {
                // Show Add button
                Button(
                    onClick = onAddToCart,
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Add")
                }
            }
        }
    }
}
