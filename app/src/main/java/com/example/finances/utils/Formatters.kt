package com.example.finances.utils


import java.text.NumberFormat
import java.util.Locale

fun formatCurrency(value: Double): String {
    val ptBrLocale = Locale.forLanguageTag("pt-BR")
    return NumberFormat.getCurrencyInstance(ptBrLocale).format(value)
}