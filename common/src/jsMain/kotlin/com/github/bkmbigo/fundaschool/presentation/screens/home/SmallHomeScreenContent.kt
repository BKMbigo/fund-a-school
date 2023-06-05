package com.github.bkmbigo.fundaschool.presentation.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.github.bkmbigo.fundaschool.presentation.components.dialog.DialogScreen
import com.github.bkmbigo.fundaschool.presentation.components.dialog.homeaction.HomeActionDialog
import com.github.bkmbigo.fundaschool.presentation.components.dialog.homeaction.HomeActionDialogAction
import com.github.bkmbigo.fundaschool.presentation.components.dialog.login.LoginDialog
import com.github.bkmbigo.fundaschool.presentation.components.dialog.login.LogoutDialog
import com.github.bkmbigo.fundaschool.presentation.components.list.HorizontalPagerList
import com.github.bkmbigo.fundaschool.presentation.components.list.HorizontalScrollableList
import com.github.bkmbigo.fundaschool.presentation.components.news.HomeNewsItem
import com.github.bkmbigo.fundaschool.presentation.components.project.ProjectItem
import com.github.bkmbigo.fundaschool.presentation.components.topbar.SmallTopBar
import com.github.bkmbigo.fundaschool.presentation.screen.home.HomeScreenAction
import com.github.bkmbigo.fundaschool.presentation.screen.home.HomeScreenState
import com.github.bkmbigo.fundaschool.presentation.theme.layoutproperties.LocalLayoutProperty

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SmallHomeScreenContent(
    state: HomeScreenState,
    onAction: (HomeScreenAction) -> Unit
) {

    val layoutProperties = LocalLayoutProperty.current

    val sectionTitlePadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp)
    val sectionListPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)

    // State Variables
    var showActionsDialog by remember { mutableStateOf(false) }

    var showLoginDialog by remember { mutableStateOf(false) }
    var showLogoutDialog by remember { mutableStateOf(false) }


    DialogScreen(
        isDialogOpen = showActionsDialog || showLoginDialog || showLogoutDialog,
        dialogContent = {
            if (showActionsDialog) {
                HomeActionDialog(
                    onAction = { action ->
                        when (action) {
                            HomeActionDialogAction.NavigateToAboutUs -> {
                                onAction(HomeScreenAction.NavigateToAboutUs)
                            }

                            HomeActionDialogAction.NavigateToAdmin -> {
                                onAction(HomeScreenAction.NavigateToAdmin)
                            }

                            HomeActionDialogAction.NavigateToDonation -> {
                                onAction(HomeScreenAction.NavigateToDonations)
                            }

                            HomeActionDialogAction.NavigateToHome -> { /*TODO*/
                            }

                            HomeActionDialogAction.NavigateToProjects -> {
                                onAction(HomeScreenAction.NavigateToProjects)
                            }

                            HomeActionDialogAction.EditProfile -> {}
                            HomeActionDialogAction.Login -> {
                                showLoginDialog = true
                            }

                            HomeActionDialogAction.Logout -> {
                                showLogoutDialog = true
                            }
                        }
                    },
                    onDismissRequest = { showActionsDialog = false }
                )
            }

            if (showLoginDialog) {
                LoginDialog(
                    onDismissRequest = {
                        showLoginDialog = false
                        showActionsDialog = false
                    }
                )
            }

            if (showLogoutDialog) {
                LogoutDialog(
                    onDismissRequest = { showLogoutDialog = false }
                )
            }

        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {

            SmallTopBar(
                modifier = Modifier.fillMaxWidth(),
                onSearch = {
                    onAction(HomeScreenAction.Search(it))
                },
                onOpenDialog = {
                    showActionsDialog = true
                }
            )

            LazyColumn(
                modifier = Modifier
                    .weight(1f, true)
                    .fillMaxWidth(),
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                item {
                    AnimatedVisibility(
                        visible = state.bookmarks.isNotEmpty(),
                    ) {
                        Text(
                            text = "Bookmarks",
                            style = layoutProperties.TextStyle.sectionTitle,
                            modifier = Modifier.padding(sectionTitlePadding)
                        )
                    }
                }
                item {
                    AnimatedVisibility(
                        visible = state.bookmarks.isNotEmpty(),
                    ) {
                        /*TODO: Add Bookmark Item*/
                    }
                }

                item {
                    AnimatedVisibility(
                        visible = state.news.isNotEmpty()
                    ) {
                        Text(
                            text = "News",
                            style = layoutProperties.TextStyle.sectionTitle,
                            modifier = Modifier.padding(sectionTitlePadding)
                        )
                    }
                }


                item {
                    AnimatedVisibility(
                        visible = state.news.isNotEmpty()
                    ) {
                        HorizontalPagerList(
                            pageCount = state.news.size,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 4.dp, vertical = 4.dp),
                            contentPadding = PaddingValues(horizontal = 8.dp),
                            pagerContent = { index ->
                                HomeNewsItem(
                                    news = state.news[index],
                                    size = DpSize(Dp.Infinity, 200.dp),
                                    modifier = Modifier.fillMaxWidth()
                                        .padding(vertical = 4.dp, horizontal = 4.dp),
                                    contentScale = ContentScale.Crop,
                                    onOpenProject = { onAction(HomeScreenAction.NavigateToProject(it)) },
                                    onOpenNews = { onAction(HomeScreenAction.NavigateToNews(it)) }
                                )
                            }
                        ) {
                            items(state.news) { newsItem ->
                                HomeNewsItem(
                                    news = newsItem,
                                    size = DpSize(Dp.Infinity, 200.dp),
                                    modifier = Modifier.fillMaxWidth()
                                        .padding(vertical = 4.dp, horizontal = 4.dp),
                                    contentScale = ContentScale.Crop,
                                    onOpenProject = { onAction(HomeScreenAction.NavigateToProject(it)) },
                                    onOpenNews = { onAction(HomeScreenAction.NavigateToNews(it)) }
                                )
                            }
                        }
                    }
                }

                item {
                    AnimatedVisibility(
                        visible = state.featuredProjects.isNotEmpty()
                    ) {
                        Text(
                            text = "Featured Projects",
                            style = layoutProperties.TextStyle.sectionTitle,
                            modifier = Modifier.padding(sectionTitlePadding)
                        )
                    }
                }

                item {
                    HorizontalScrollableList(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                        contentPadding = PaddingValues(horizontal = 4.dp)
                    ) {
                        items(state.featuredProjects) { projectItem ->
                            ProjectItem.FeaturedProject(
                                project = projectItem,
                                size = DpSize(250.dp, 350.dp),
                                modifier = Modifier,
                                isProjectBookmarked = false,
                                onProjectBookmarked = {},
                                onProjectOpened = {
                                    onAction(HomeScreenAction.NavigateToProject(projectItem))
                                }
                            )
                        }
                    }
                }

            }
        }
    }
}