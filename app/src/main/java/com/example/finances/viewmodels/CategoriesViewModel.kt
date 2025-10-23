package com.example.finances.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

// Esta classe segura o ESTADO e a LÓGICA da tela (sobrevive a girar tela, etc.)

class CategoriesViewModel: ViewModel() {

    //(List)
    private val _categories = MutableStateFlow(
        listOf("Teste")
    )
    val categories: StateFlow<List<String>> = _categories.asStateFlow()

    //(TextField)
    private val _newCategoryName = MutableStateFlow("")
    val newCategoryName: StateFlow<String> = _newCategoryName.asStateFlow()

    //Function
    fun onCategoryNameChange(newName: String) {
        _newCategoryName.value = newName
    }

    fun addCategory() {
        val name = _newCategoryName.value
        if (name.isNotBlank()) {
            _categories.update { actualList -> actualList + name}
            _newCategoryName.value = ""
        }
    }

    fun removeCategory(category: String) {
        _categories.update { actualList -> actualList - category}
    }
}
