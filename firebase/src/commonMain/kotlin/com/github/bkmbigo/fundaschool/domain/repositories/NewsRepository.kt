package com.github.bkmbigo.fundaschool.domain.repositories

import com.github.bkmbigo.fundaschool.domain.models.News
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun insertNews(news: News)
    suspend fun updateNews(news: News)
    suspend fun deleteNews(news: News)
    suspend fun getNews(newsId: String): News?
    suspend fun getAllNews(): List<News>
    fun observeNews(): Flow<List<News>>

}