package com.github.bkmbigo.fundaschool.presentation.screens.home

import com.github.bkmbigo.fundaschool.domain.models.firebase.Donation
import com.github.bkmbigo.fundaschool.domain.models.firebase.News
import com.github.bkmbigo.fundaschool.domain.models.firebase.Project
import com.github.bkmbigo.fundaschool.domain.models.firebase.User
import com.github.bkmbigo.fundaschool.domain.repositories.firebase.DonationRepository
import com.github.bkmbigo.fundaschool.domain.repositories.firebase.NewsRepository
import com.github.bkmbigo.fundaschool.domain.repositories.firebase.ProjectRepository
import com.github.bkmbigo.fundaschool.domain.repositories.firebase.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class HomeScreenPresenter(
    private val projectRepository: ProjectRepository,
    private val newsRepository: NewsRepository,
    private val donationRepository: DonationRepository,
    private val userRepository: UserRepository,
    private val coroutineScope: CoroutineScope
) {


    private val _state = MutableStateFlow(HomeScreenState())
    val state = _state.asStateFlow()

    private var _allNews = emptyList<News>()
    private var _allProjects = emptyList<Project>()
    private var _allDonations = emptyList<Donation>()


    init {
        observeNews()
        observeProjects()
        observeDonations()
    }

    private fun observeNews() {
        coroutineScope.launch {
            _allNews = newsRepository.getAllNews()

            _state.value = _state.value.copy(
                news = _allNews.filterNews()
            )

        }
    }

    private fun observeProjects() {
        coroutineScope.launch {
            _allProjects = projectRepository.getAllProjects()
            _state.value = _state.value.copy(
                featuredProjects = _allProjects.getFeaturedProjects(),
                recentlyEndedProjects = _allProjects.getRecentlyProjects()
            )
        }
    }

    private fun observeDonations() {
        coroutineScope.launch {
            _allDonations = donationRepository.getAllDonations()
            _state.value = _state.value.copy(
                topDonors = _allDonations.getTopDonors()
            )

        }
    }

    private fun List<News>.filterNews(): List<News> = this.sortedBy { it.uploadDate }.take(5)

    private fun List<Project>.getFeaturedProjects(): List<Project> =
        this.filter { it.featured }

    private fun List<Project>.getRecentlyProjects(): List<Project> =
        this.filter {
            it.completionDate > Clock.System.now()
                .toLocalDateTime(TimeZone.currentSystemDefault()).date.toEpochDays() - 30
        }

    private suspend fun List<Donation>.getTopDonors(): List<User> =
        this
            .groupBy { it.userId }
            .mapValues { list -> list.value.map { it.amount }.sum() }
            .toList().sortedBy { it.second }
            .take(5)
            .map {
                it.first?.let { it1 -> getUser(it1) }
            }.filterNotNull()


    private suspend fun getUser(userId: String): User? =
        try {
            userRepository.getUser(userId)
        } catch (e: Exception) {
            null
        }


}