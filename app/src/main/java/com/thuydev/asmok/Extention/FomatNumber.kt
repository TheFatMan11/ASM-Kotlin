package com.thuydev.asmok.Extention

import java.text.DecimalFormat


fun formatNumber(number: Number): String {
    val formatter = DecimalFormat("#,###")
    return formatter.format(number)
}