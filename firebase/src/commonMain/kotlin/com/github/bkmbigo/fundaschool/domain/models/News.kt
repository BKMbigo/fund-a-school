package com.github.bkmbigo.fundaschool.domain.models

import com.github.bkmbigo.fundaschool.domain.utils.NewsCategory
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class News(
    val id: String,
    val title: String,
    val caption: String,
    val category: NewsCategory,
    val uploadDate: LocalDate,
    val text: String,
    val author: String,
    val associatedProject: Project?,
    val media: List<Media>
)
