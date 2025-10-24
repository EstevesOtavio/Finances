package com.example.finances.viewmodels

import androidx.lifecycle.ViewModel
import com.example.finances.data.CategorySummary
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CategoriesViewModel: ViewModel() {
    private val _categoriesSummary = MutableStateFlow(
        listOf(
            CategorySummary(1, "Mercado", 0.0),
            CategorySummary(2, "Água", 0.0),
            CategorySummary(3, "Energia", 0.0),
            CategorySummary(4, "Farmácia", 0.0),
            CategorySummary(5, "Fixas", 0.0)
        )
    )
    val categoriesSummary: StateFlow<List<CategorySummary>> = _categoriesSummary.asStateFlow()
}