package com.example.platform.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class DroidSpace(
    val value: Dp
){
    None(0.dp),
    Small(2.dp),
    MSmall(4.dp),
    XSmall(8.dp),
    Medium(14.dp),
    MMedium(16.dp),
    XMedium(20.dp),
    Large(24.dp),
    MLarge(32.dp),
    XLarge(56.dp),
    ExtraLarge(62.dp),
    XExtraLarge(78.dp)
}