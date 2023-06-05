package com.github.bkmbigo.fundaschool.presentation.components.project

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import com.github.bkmbigo.fundaschool.domain.models.firebase.Project

object ProjectItem {
    @Composable
    operator fun invoke(
        project: Project,
        isProjectBookmarked: Boolean = false,
        modifier: Modifier = Modifier,
        onProjectOpened: () -> Unit,
        onBookmarkChanged: () -> Unit
    ) = ProjectListItem(
        project = project,
        isProjectBookmarked = isProjectBookmarked,
        modifier = modifier,
        onProjectOpened = onProjectOpened,
        onBookmarkChanged = onBookmarkChanged
    )

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun FeaturedProject(
        project: Project,
        size: DpSize,
        modifier: Modifier = Modifier,
        isProjectBookmarked: Boolean = false,
        onProjectBookmarked: () -> Unit = {},
        onProjectOpened: () -> Unit = {}
    ) = featuredProject(
        project = project,
        size = size,
        modifier = modifier,
        isProjectBookmarked = isProjectBookmarked,
        onProjectBookmarked = onProjectBookmarked,
        onProjectOpened = onProjectOpened
    )

    @Composable
    fun BookmarkedProject(
        project: Project,
        size: DpSize,
        modifier: Modifier = Modifier
    ) = bookmarkedProject(
        project = project,
        size = size,
        modifier = modifier
    )

    @Composable
    fun RecentlyEndedProject(
        project: Project,
        size: DpSize,
        modifier: Modifier = Modifier,
        onOpenProject: () -> Unit,
    ) = recentlyEndedProject(
        project,
        size,
        modifier,
        onOpenProject
    )
}