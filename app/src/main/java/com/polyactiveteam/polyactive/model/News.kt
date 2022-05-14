package com.polyactiveteam.polyactive.model

data class News(
    val imageId: Int,
    val groupType: Group,
    val header: String,
    val newsDescription: String,
    val date: Long,
    val likeCounter: Int
)
