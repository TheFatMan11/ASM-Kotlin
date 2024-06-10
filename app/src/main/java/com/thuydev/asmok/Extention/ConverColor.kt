package com.thuydev.asmok.Extention
import androidx.compose.ui.graphics.Color



fun colorFromHex(hex: String): Color {
    return Color(android.graphics.Color.parseColor(hex))
}