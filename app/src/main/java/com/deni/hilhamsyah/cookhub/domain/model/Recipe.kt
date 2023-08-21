package com.deni.hilhamsyah.cookhub.domain.model

import com.deni.hilhamsyah.cookhub.util.TimeUnit
import com.deni.hilhamsyah.cookhub.util.WeightUnit

data class Recipe(
    val id: String,
    val userId: String,
    val title: String,
    val description: String,
    val image: String,
    val video: String?,
    val ingredients: List<Pair<String, Pair<Int, WeightUnit>>>,
    val cookTime: Pair<Int, TimeUnit>
)