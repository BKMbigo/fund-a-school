package com.github.bkmbigo.fundaschool.presentation.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.github.bkmbigo.fundaschool.domain.models.Media
import com.github.bkmbigo.fundaschool.domain.models.News
import com.github.bkmbigo.fundaschool.domain.models.Project
import com.github.bkmbigo.fundaschool.domain.utils.MediaType
import com.github.bkmbigo.fundaschool.domain.utils.NewsCategory
import com.github.bkmbigo.fundaschool.presentation.components.dialog.SidebarScreen
import com.github.bkmbigo.fundaschool.presentation.components.horizontallist.HorizontalScrollableList
import com.github.bkmbigo.fundaschool.presentation.components.news.HomeNewsItem
import com.github.bkmbigo.fundaschool.presentation.components.project.HomeProjectItem
import com.github.bkmbigo.fundaschool.presentation.components.sidebar.HomeSidebar
import com.github.bkmbigo.fundaschool.presentation.components.sidebar.HomeSidebarAction
import com.github.bkmbigo.fundaschool.presentation.components.topbar.HomeTopBar
import com.github.bkmbigo.fundaschool.presentation.components.topbar.HomeTopBarAction
import com.github.bkmbigo.fundaschool.presentation.screens.news.NewsScreen
import com.github.bkmbigo.fundaschool.presentation.utils.FormFactor
import kotlinx.datetime.*
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class HomeScreen(
    val formFactor: FormFactor
) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current

        var state by remember {
            mutableStateOf(
                getDefaultHomeScreenState(formFactor).copy(
                    news = MutableList(8) { index ->
                        News(
                            id = "seda",
                            title = "President launches \'Linda Mschana\' $index",
                            caption = "President lauches a project to avail sanitary pads to pupils in Turkana County",
                            category = NewsCategory.NEW_PROJECTS,
                            uploadDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
                            text = "",
                            author = "Brian Mbigo",
                            associatedProject = null,
                            media = listOf(
                                Media(
                                    id = "rere",
                                    mediaType = MediaType.IMAGE,
                                    url = "https://images.unsplash.com/photo-1473649085228-583485e6e4d7?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1032&q=80"
                                )
                            )

                        )
                    }.toList(),
                    featuredProjects = MutableList(12) { index ->
                        Project(
                            id = "ind $index",
                            name = "Classroom construction in Nakuru $index",
                            description = "Introducing the \"Classroom Construction in Nakuru County\" project, an initiative driven by the transformative power of education. Nakuru County has long been plagued by a dire shortage of classrooms, hindering the learning process and limiting the potential of thousands of students. Overcrowded spaces, dilapidated structures, and inadequate facilities have cast a shadow over their educational journey, jeopardizing their prospects for a brighter future.\n" +
                                    "\n" +
                                    "But now, there is hope. Fund-a-School, a revolutionary Android application, is stepping forward to address this pressing issue. With unwavering determination, the app aims to raise funds for the construction of much-needed classrooms in three key schools: Jielimishe School, Jitahidi School, and Jiinue School. These institutions are the heart of education in Nakuru County, nurturing young minds and fostering a thirst for knowledge.\n" +
                                    "\n" +
                                    "By donating to this noble cause through the Fund-a-School app, you become a beacon of hope for these deserving students. Your contribution will enable the realization of safe and conducive learning environments, empowering students to thrive academically and unlock their full potential. Together, let us bridge the gap in education and ensure that every child in Nakuru County has access to quality education. Donate now and be a part of this transformative journey!",
                            featured = true,
                            schools = listOf("Menengai Secondary School"),
                            startDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date.also { it.minus(28, DateTimeUnit.DAY) },
                            completionDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date.also { it.plus(28, DateTimeUnit.DAY) },
                            targetAmount = 2_000_000.0f,
                            currentAmount = 0.0f,
                            donors = 20,
                            media = listOf(
                                Media(
                                    id = "rere",
                                    mediaType = MediaType.IMAGE,
                                    url = "https://images.unsplash.com/photo-1473649085228-583485e6e4d7?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1032&q=80"
                                )
                            )
                        )
                    }.toList()
                )
            )
        }

        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            HomeScreenContent(
                state = state,
                onAction = { action ->
                    when (action) {
                        HomeScreenAction.NavigateToAboutUs -> {}
                        HomeScreenAction.NavigateToDonations -> {}
                        HomeScreenAction.NavigateToProjects -> {}
                        HomeScreenAction.NavigateToAllNews -> {}
                        HomeScreenAction.OpenCloseSidebar -> {
                            if (state is HomeScreenState.SmallHomeScreenState) {
                                state =
                                    (state as HomeScreenState.SmallHomeScreenState).copy(isSidebarOpen = !(state as HomeScreenState.SmallHomeScreenState).isSidebarOpen)
                            }
                        }

                        HomeScreenAction.OpenHomeOptionsDialog -> {

                        }

                        is HomeScreenAction.NavigateToNews -> {
                            navigator?.push(NewsScreen(action.news, formFactor))
                        }
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent(
    state: HomeScreenState,
    onAction: (HomeScreenAction) -> Unit
) {
    SidebarScreen(
        isSidebarOpen = state is HomeScreenState.SmallHomeScreenState && state.isSidebarOpen,
        sidebarContent = {
            HomeSidebar(
                onDismissRequest = {
                    onAction(HomeScreenAction.OpenCloseSidebar)
                },
                onAction = { action ->
                    when (action) {
                        HomeSidebarAction.NavigateToAboutUs -> {}
                        HomeSidebarAction.NavigateToDonations -> {}
                        HomeSidebarAction.NavigateToHome -> {}
                        HomeSidebarAction.NavigateToProjects -> {}
                        HomeSidebarAction.NavigateUp -> {}
                    }
                }
            )

        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            HomeTopBar(
                formFactor = when (state) {
                    is HomeScreenState.LargeHomeScreenState -> FormFactor.LARGE
                    is HomeScreenState.MobileHomeScreenState -> FormFactor.MOBILE
                    is HomeScreenState.SmallHomeScreenState -> FormFactor.SMALL
                },
                onAction = { action ->
                    when (action) {
                        HomeTopBarAction.NavigateToAboutUs -> {}
                        HomeTopBarAction.NavigateToDonation -> {}
                        HomeTopBarAction.NavigateToNews -> {}
                        HomeTopBarAction.NavigateToProjects -> {}
                        is HomeTopBarAction.SearchText -> {}
                        HomeTopBarAction.ShowOptionsDialog -> run {
                            onAction(HomeScreenAction.OpenHomeOptionsDialog)
                        }

                        HomeTopBarAction.ShowSideNavigation -> {
                            onAction(HomeScreenAction.OpenCloseSidebar)
                        }
                    }
                }
            )

            Spacer(modifier = Modifier.height(4.dp))


            Text(
                text = "News",
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(start = 4.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            HorizontalScrollableList(
                modifier = Modifier
                    .fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(state.news) { news ->
                    HomeNewsItem(
                        news,
                        modifier = Modifier.size(width = 400.dp, height = 250.dp).padding(horizontal = 4.dp),
                        onOpenProject = {},
                        onOpenNews = {
                            onAction(HomeScreenAction.NavigateToNews(it))
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Featured Projects",
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(start = 4.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            HorizontalScrollableList(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) { 
                items(state.featuredProjects) { projectItem ->
                    HomeProjectItem.FeaturedProject(
                        projectItem,
                        modifier = Modifier
                            .width(300.dp)
                            .height(400.dp)
                            .padding(horizontal = 8.dp)
                    )
                }
            }
        }
    }
}