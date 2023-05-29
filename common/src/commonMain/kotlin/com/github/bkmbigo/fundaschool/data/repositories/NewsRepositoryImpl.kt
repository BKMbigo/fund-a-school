package com.github.bkmbigo.fundaschool.data.repositories

import com.github.bkmbigo.fundaschool.data.util.NewsItem
import com.github.bkmbigo.fundaschool.data.util.NewsItem.Companion.toNewsItem
import com.github.bkmbigo.fundaschool.data.util.ProjectItem
import com.github.bkmbigo.fundaschool.data.util.ProjectItem.Companion.toProjectItem
import com.github.bkmbigo.fundaschool.domain.models.Media
import com.github.bkmbigo.fundaschool.domain.models.News
import com.github.bkmbigo.fundaschool.domain.models.Project
import com.github.bkmbigo.fundaschool.domain.repositories.NewsRepository
import dev.gitlive.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NewsRepositoryImpl(
    firestore: FirebaseFirestore
): NewsRepository {
    private val collectionReference = firestore.collection("news")
    private val projectReference = firestore.collection("project")
    private val mediaReference = firestore.collection("media")
    override suspend fun insertNews(news: News) {
        val media = news.media.map { mediaItem ->
            mediaReference.add(Media.serializer(), mediaItem).id
        }
        val associatedProject = news.associatedProject?.id

        val id = collectionReference.add(NewsItem.serializer(), news.toNewsItem(associatedProject, media)).id
        collectionReference.document(id).update("id" to id)
    }

    override suspend fun updateNews(news: News) {
        val media = news.media.map { mediaItem ->
            val id = mediaReference.add(Media.serializer(), mediaItem).id
            mediaReference.document(id).update("id" to id)
            id
        }

        val associatedProject = news.associatedProject?.id

        collectionReference.document(news.id).update(
            "title" to news.title,
            "caption" to news.caption,
            "category" to news.category,
            "uploadDate" to news.uploadDate.toEpochDays(),
            "text" to news.text,
            "author" to news.author,
            "associatedProject" to associatedProject,
            "media" to media
        )
    }

    override suspend fun deleteNews(news: News) {
        news.media.forEach { mediaItem ->
            mediaReference.document(mediaItem.id).delete()
        }

        collectionReference.document(news.id).delete()
    }

    override suspend fun getNews(newsId: String): News =
        collectionReference.document(newsId).get().data<NewsItem>().generateNews()

    override suspend fun getAllNews(): List<News> =
        collectionReference.get().documents.map { it.data<NewsItem>().generateNews() }

    override fun observeNews(): Flow<List<News>> =
        collectionReference.snapshots.map { snapshot ->
            snapshot.documents.map { it.data<NewsItem>().generateNews() }
        }

    private suspend fun NewsItem.generateNews(): News {
        val mediaList = media.map { item ->
            mediaReference.document(item).get().data<Media>()
        }

        val project = associatedProject?.let { it ->
            projectReference.document(it).get().data<ProjectItem>().generateProject()
        }

        return toNews(project, mediaList)
    }

    private suspend fun ProjectItem.generateProject(): Project {
        val mediaList = media.map { item ->
            mediaReference.document(item).get().data<Media>()
        }

        return toProject(mediaList)
    }
}