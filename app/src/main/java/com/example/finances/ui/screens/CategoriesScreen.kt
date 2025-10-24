package com.example.finances.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finances.data.CategorySummary
import com.example.finances.ui.theme.FinancesTheme
import com.example.finances.utils.formatCurrency
import com.example.finances.viewmodels.CategoriesViewModel

@Composable
fun CategoriesScreen(
    modifier: Modifier = Modifier,
    viewModel: CategoriesViewModel = viewModel()
)  {
    val categories by viewModel.categoriesSummary.collectAsStateWithLifecycle()
  
    CategoriesContent(
        modifier = modifier,
        categories = categories,
        formatCurrency = { formatCurrency(it) },
        onCategoryClick = { /* TODO: Navegar */}
    )
}

@Composable
fun CategoriesContent(
    modifier: Modifier = Modifier,
    categories: List<CategorySummary>,
    formatCurrency: (Double) -> String,
    onCategoryClick: (CategorySummary) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Janeiro",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(categories) { category ->
                CategoryItem(
                    categoryName = category.categoryName,
                    totalAmountFormatted = formatCurrency(category.totalAmount),
                    onClick = {
                        onCategoryClick(category)
                    }
                )
            }
        }
    }
}

@Composable
fun CategoryItem(
    categoryName: String,
    totalAmountFormatted: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = MaterialTheme.shapes.medium,
        tonalElevation = 1.dp
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp, vertical = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = categoryName,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = totalAmountFormatted,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoriesScreenPreview() {
    FinancesTheme {
        val previewCategories = listOf(
            CategorySummary(1, "Mercado", 1250.75),
            CategorySummary(2, "Agua", 150.20)
        )
        CategoriesContent(
            categories = previewCategories,
            formatCurrency = { formatCurrency(it)},
            onCategoryClick = {}
        )
    }
}
