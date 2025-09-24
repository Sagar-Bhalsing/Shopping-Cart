# ShoppingCart

A simple Android shopping cart app built with Kotlin, Retrofit, Room Database, Dagger Hilt, and MVVM architecture, fetching products from the Fake Store API.

## App Design
- **Architecture**: MVVM for clean separation of concerns.
- **UI**: (Optional) Jetpack Compose for modern, reactive UI; otherwise, traditional XML layouts.
- **Data Flow**: Retrofit for API calls, Room for cart persistence, and StateFlow/LiveData for reactive updates.
- **Screens**:
  - **Product List**: Shows product titles and prices with "Add to Cart" buttons.
  - **Cart Screen**: Displays cart items, total price, and "Remove" buttons.

## Features
- Fetch and display products (title, price) from `https://fakestoreapi.com/products`.
- Add items to cart, stored in Room Database.
- View cart with item list and total value.
- Remove items from cart, updating Room Database.

## APK
- Download the app: [ShoppingCart APK](https://drive.google.com/file/d/1bgW7oBQCNWsQVcgI6SQyovKOBH_aQdQj/view?usp=sharing)
