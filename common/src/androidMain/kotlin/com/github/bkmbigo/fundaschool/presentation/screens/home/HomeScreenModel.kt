package com.github.bkmbigo.fundaschool.presentation.screens.home

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.github.bkmbigo.fundaschool.data.persistence.PersistenceKey
import com.github.bkmbigo.fundaschool.data.persistence.settings
import com.github.bkmbigo.fundaschool.domain.models.firebase.Donation
import com.github.bkmbigo.fundaschool.domain.models.firebase.News
import com.github.bkmbigo.fundaschool.domain.models.firebase.Project
import com.github.bkmbigo.fundaschool.domain.models.firebase.User
import com.github.bkmbigo.fundaschool.domain.models.persistence.PendingDonation
import com.github.bkmbigo.fundaschool.domain.repositories.firebase.DonationRepository
import com.github.bkmbigo.fundaschool.domain.repositories.firebase.NewsRepository
import com.github.bkmbigo.fundaschool.domain.repositories.firebase.ProjectRepository
import com.github.bkmbigo.fundaschool.domain.repositories.firebase.UserRepository
import com.russhwolf.settings.get
import com.russhwolf.settings.set
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class HomeScreenModel(
    private val projectRepository: ProjectRepository,
    private val newsRepository: NewsRepository,
    private val donationRepository: DonationRepository,
    private val userRepository: UserRepository
): ScreenModel {

    private val _state = MutableStateFlow(HomeScreenState())
    val state = _state.asStateFlow()

    private var _allNews = emptyList<News>()
    private var _allProjects = emptyList<Project>()
    private var _allDonations = emptyList<Donation>()


    init {
        observeNews()
        observeProjects()
        observeDonations()

        val pendingDonationTitle: String? = settings[PersistenceKey.pendingDonationTitle]

        if(pendingDonationTitle != null) {
            _state.value = _state.value.copy(
                pendingDonation = PendingDonation(
                    amount = settings[PersistenceKey.pendingDonationAmount] ?: 0.00f,
                    projectTitle = pendingDonationTitle ?: ""
                )
            )

        }
    }

    fun clearPendingDonation() {
        _state.value = _state.value.copy(pendingDonation = null)
        settings.set(PersistenceKey.pendingDonationAmount, null)
        settings.set(PersistenceKey.pendingDonationTitle, null)
    }

    private fun observeNews() {
        coroutineScope.launch {
            newsRepository.observeNews().collect{ newNews ->
                _allNews = newNews
                _state.value = _state.value.copy(
                    news = newNews.filterNews()
                )
            }
        }
    }

    private fun observeProjects() {
        coroutineScope.launch {
            projectRepository.observeProjects().collect{ newProjects ->
                _allProjects = newProjects
                _state.value = _state.value.copy(
                    featuredProjects = newProjects.getFeaturedProjects(),
                    recentlyEndedProjects = newProjects.getRecentlyProjects()
                )
            }
        }
    }

    private fun observeDonations() {
        coroutineScope.launch {
            donationRepository.observeDonations().collect{ newDonations ->
                _allDonations = newDonations
                _state.value = _state.value.copy(
                    topDonors = newDonations.getTopDonors()
                )
            }
        }
    }
    private fun List<News>.filterNews(): List<News> = this.sortedBy { it.uploadDate }.take(5)

    private fun List<Project>.getFeaturedProjects(): List<Project> =
        this.filter { it.featured }

    private fun List<Project>.getRecentlyProjects(): List<Project> =
        this.filter { it.completionDate > Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date.toEpochDays() - 30 }

    private suspend fun List<Donation>.getTopDonors(): List<User> =
        this
            .groupBy { it.userId }
            .mapValues { list -> list.value.map { it.amount }.sum() }
            .toList().sortedBy { it.second }
            .take(5)
            .map {
                it.first?.let { it1 -> getUser(it1) }
            }.filterNotNull()



    private suspend fun getUser(userId: String): User?  =
        try {
            userRepository.getUser(userId)
        } catch (e: Exception) { null }


}