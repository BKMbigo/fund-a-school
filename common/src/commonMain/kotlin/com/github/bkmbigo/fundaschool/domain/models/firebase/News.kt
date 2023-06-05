package com.github.bkmbigo.fundaschool.domain.models.firebase

import com.github.bkmbigo.fundaschool.domain.utils.NewsCategory
import kotlinx.datetime.LocalDate

data class News(
    val id: String,
    val title: String,
    val caption: String,
    val category: NewsCategory,
    val uploadDate: LocalDate,
    val text: String,
    val author: String,
    val associatedProject: Project?,
    val mediaUrl: String
)
