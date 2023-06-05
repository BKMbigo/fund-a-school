package com.github.bkmbigo.fundaschool.data.firebase.repositories

import com.github.bkmbigo.fundaschool.data.firebase.repositories.utils.decodeNewsItem
import com.github.bkmbigo.fundaschool.data.firebase.repositories.utils.decodeProject
import com.github.bkmbigo.fundaschool.data.firebase.repositories.utils.encodeNewsItem
import com.github.bkmbigo.fundaschool.data.firebase.util.NewsItem
import com.github.bkmbigo.fundaschool.data.firebase.util.NewsItem.Companion.toNewsItem
import com.github.bkmbigo.fundaschool.domain.models.firebase.News
import com.github.bkmbigo.fundaschool.domain.models.firebase.Project
import com.github.bkmbigo.fundaschool.domain.repositories.firebase.NewsRepository
import com.github.bkmbigo.fundaschool.utils.LogInfo
import dev.gitlive.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull

class NewsRepositoryImpl(
    firestore: FirebaseFirestore
) : NewsRepository {
    private val collectionReference = firestore.collection("news")
    private val projectReference = firestore.collection("project")
    private val mediaReference = firestore.collection("media")
    override suspend fun insertNews(news: News) {
        val associatedProject = news.associatedProject?.id

        val id =
            collectionReference.add(news.toNewsItem(associatedProject), { it.encodeNewsItem() }).id
        collectionReference.document(id).update(mapOf("id" to id))
    }

    override suspend fun updateNews(news: News) {

        val associatedProject = news.associatedProject?.id

        collectionReference.document(news.id).update(
            mapOf(
                "title" to news.title,
                "caption" to news.caption,
                "category" to news.category,
                "uploadDate" to news.uploadDate.toEpochDays(),
                "text" to news.text,
                "author" to news.author,
                "associatedProject" to associatedProject,
                "mediaUrl" to news.mediaUrl
            )
        )
    }

    override suspend fun deleteNews(news: News) {
        collectionReference.document(news.id).delete()
    }

    override suspend fun getNews(newsId: String): News? =
        collectionReference.document(newsId).get().data<NewsItem>({ this.decodeNewsItem() })
            ?.generateNews()

    override suspend fun getAllNews(): List<News> =
        collectionReference.get().documents.map {
            val news = it.data<NewsItem> {
                this.decodeNewsItem()
            }?.generateNews()
            LogInfo("News is ${news}")
            news
        }.filterNotNull()

    override fun observeNews(): Flow<List<News>> =
        collectionReference.snapshots.map { snapshot ->
            snapshot.documents.map {
                val newsItem = it.data<NewsItem>({ this.decodeNewsItem() })
                newsItem?.generateNews()
            }.filterNotNull()
        }.filterNotNull()

    private suspend fun NewsItem.generateNews(): News {

        val project = associatedProject?.let { it ->
            projectReference.document(it).get().data<Project>({ this.decodeProject() })
        }

        return toNews(project)
    }
}