package com.example.ecommerceapp.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ecommerceapp.model.Category
import com.example.ecommerceapp.model.Product

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavController,
    onProfileClick: () -> Unit,
    onCartClick: () -> Unit
) {
    Scaffold(
        topBar = { MyTopApBar(onCartClick,onProfileClick) },
        bottomBar = { BottomNavigationBar() }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
//            Search Section
            val searchQuery = remember { mutableStateOf("") }
            val focusManager = LocalFocusManager.current
            SearchBar(
                query = searchQuery.value,
                onQueryChange = { searchQuery.value = it },
                onSearch = {
//                    todo("DO the search logic")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
//            Search Result Section


//            Categories Section
            SectionTitle(title = "Categories", actionText = "See All") {
//                TODO() add navigation
            }
            val categories: List<Category> =
                listOf(
                    Category(
                        1,
                        "Electronics",
                        "https://cdn-icons-png.flaticon.com/512/1555/1555401.png"
                    ),
                    Category(
                        2,
                        "Clothing",
                        "https://cdn-icons-png.flaticon.com/512/2935/2935183.png"
                    )
                )

            val selectedCategory = remember { mutableStateOf<Category?>(null) }
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(categories.size) {
                    CategoryChip(
                        icon = categories[it].iconUrl,
                        text = categories[it].name,
                        isSelected = categories[it] == selectedCategory.value,
                        onClick = {
                            selectedCategory.value = categories[it]
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))


//            Featured Products Section
            Spacer(modifier = Modifier.height(16.dp))
            SectionTitle(
                title = "Featured", actionText = "See All"
            ) {

            }
            val productsList = listOf<Product>(
                Product(
                    id = "1",
                    name = "Smartphone",
                    price = 500.0,
                    imageUrl = "https://cdn.britannica.com/38/162538-004-6C7EBD5B.jpg"
                ),
                Product(
                    id = "2",
                    name = "Laptop",
                    price = 1000.0,
                    imageUrl = "https://cdn.britannica.com/38/162538-004-6C7EBD5B.jpg"
                )
            )
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(productsList) { product ->
                    FeaturedProductCard(product = product) {
//                        Handle the click event here
                    }
                }
            }
        }
    }
}

