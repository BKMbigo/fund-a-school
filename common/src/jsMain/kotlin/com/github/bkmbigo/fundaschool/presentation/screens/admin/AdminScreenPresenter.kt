package com.github.bkmbigo.fundaschool.presentation.screens.admin

import com.github.bkmbigo.fundaschool.domain.models.firebase.Donation
import com.github.bkmbigo.fundaschool.domain.models.firebase.Project
import com.github.bkmbigo.fundaschool.domain.repositories.firebase.AuthRepository
import com.github.bkmbigo.fundaschool.domain.repositories.firebase.DonationRepository
import com.github.bkmbigo.fundaschool.domain.repositories.firebase.NewsRepository
import com.github.bkmbigo.fundaschool.domain.repositories.firebase.ProjectRepository
import com.github.bkmbigo.fundaschool.domain.repositories.firebase.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class AdminScreenPresenter(
    private val donationRepository: DonationRepository,
    private val projectRepository: ProjectRepository,
    private val newsRepository: NewsRepository,
    private val userRepository: UserRepository,
    private val authRepository: AuthRepository,
    private val coroutineScope: CoroutineScope
) {

    private val _state = MutableStateFlow(AdminScreenState())
    val state = _state.asStateFlow()

    private val todayDateEpochs = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date.toEpochDays()

    init {
        coroutineScope.launch {
            val allProjects = async { projectRepository.getAllProjects() }
            val allDonations = async { donationRepository.getAllDonations() }
            val allNews = async { newsRepository.getAllNews() }
            val isAdmin = async {
                authRepository.currentUser()?.let {
                    userRepository.getUser(it.uid)?.admin
                } ?: false
            }

            _state.value = _state.value.copy(
                completedProjects = allProjects.await().getCompletedProjects(),
                ongoingProjects = allProjects.await().getOngoingProjects(),
                totalDonations = allDonations.await().getTotalDonation(),
                totalDonors = allDonations.await().getTotalDonors(),
                news = allNews.await(),
                projects = allProjects.await(),
                donations = allDonations.await(),
                isAdmin = isAdmin.await()
            )
        }

    }

    private fun List<Project>.getCompletedProjects(): Int = this.count { it.completionDate < todayDateEpochs }

    private fun List<Project>.getOngoingProjects(): Int = this.count { it.completionDate >= todayDateEpochs }

    private fun List<Donation>.getTotalDonors(): Int = this.groupBy { it.userId }.size

    private fun List<Donation>.getTotalDonation(): Float = this.map { it.amount }.sum()

}