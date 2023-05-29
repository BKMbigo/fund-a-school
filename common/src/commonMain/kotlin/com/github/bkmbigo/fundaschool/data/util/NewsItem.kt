package com.github.bkmbigo.fundaschool.data.util

import com.github.bkmbigo.fundaschool.domain.models.Media
import com.github.bkmbigo.fundaschool.domain.models.News
import com.github.bkmbigo.fundaschool.domain.models.Project
import com.github.bkmbigo.fundaschool.domain.utils.NewsCategory
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class NewsItem(
    val id: String,
    val title: String,
    val caption: String,
    val category: NewsCategory,
    val uploadDate: Int,
    val text: String,
    val author: String,
    val associatedProject: String?,
    val media: List<String>
) {

    fun toNews(associatedProject: Project?, mediaList: List<Media>): News = News(
        id = id,
        title = title,
        caption = caption,
        category = category,
        uploadDate = LocalDate.fromEpochDays(uploadDate),
        text = text,
        author = author,
        associatedProject = associatedProject,
        media = mediaList
    )

    companion object {
        fun News.toNewsItem(associatedProject: String?, mediaList: List<String>): NewsItem =
            NewsItem(
                id = id,
                title = title,
                caption = caption,
                category = category,
                uploadDate = uploadDate.toEpochDays(),
                text = text,
                author = author,
                associatedProject = associatedProject,
                media = mediaList
            )

    }
}
