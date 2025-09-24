package com.assign.androidcart.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assign.androidcart.data.db.CartItemEntity
import com.assign.androidcart.data.model.ProductDto
import com.assign.androidcart.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class UiState(
    val products: List<ProductDto> = emptyList(),
    val cartItems: List<CartItemEntity> = emptyList(),
    val loading: Boolean = false,
    val error: String? = null
)
@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: CartRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState(loading = true))
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        observeCart()
        fetchProducts()
    }

    private fun observeCart() {
        repo.getCartItems().onEach { items ->
            _uiState.update { it.copy(cartItems = items) }
        }.launchIn(viewModelScope)
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            _uiState.update { it.copy(loading = true, error = null) }
            try {
                val products = repo.fetchProducts()
                _uiState.update { it.copy(products = products, loading = false) }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message ?: "Unknown", loading = false) }
            }
        }
    }

    fun addToCart(product: ProductDto) {
        viewModelScope.launch {
            repo.addToCart(product)
        }
    }

    fun incrementQuantity(item: CartItemEntity) {
        viewModelScope.launch {
            repo.updateQuantity(item.copy(quantity = item.quantity + 1))
        }
    }

    fun decrementQuantity(item: CartItemEntity) {
        viewModelScope.launch {
            if (item.quantity > 1) {
                repo.updateQuantity(item.copy(quantity = item.quantity - 1))
            } else {
                repo.removeFromCart(item)
            }
        }
    }

    fun totalCartValue(): Double {
        return _uiState.value.cartItems.sumOf { it.price * it.quantity }
    }
}