package com.example.testtask.utils

import java.text.NumberFormat
import java.util.Locale

fun Double.toFormattedPrice(): String {
    return String.format("%.2f ₽/шт", this)
}

fun Double.toFormattedString(): String {
    val numberFormat = NumberFormat.getNumberInstance(Locale("ru", "RU"))
    return numberFormat.format(this)
}

fun String?.toImageUrl(): String {
    return this?.let { "https://img.napolke.ru/image/get?uuid=$it" } ?: ""
}

