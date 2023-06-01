package com.github.bkmbigo.fundaschool.presentation.screens.home

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.github.bkmbigo.fundaschool.domain.models.Media
import com.github.bkmbigo.fundaschool.domain.models.News
import com.github.bkmbigo.fundaschool.domain.utils.MediaType
import com.github.bkmbigo.fundaschool.domain.utils.NewsCategory
import com.github.bkmbigo.fundaschool.presentation.components.dialog.homeaction.HomeActionDialog
import com.github.bkmbigo.fundaschool.presentation.components.dialog.homeaction.HomeActionDialogAction
import com.github.bkmbigo.fundaschool.presentation.components.dialog.login.LoginDialog
import com.github.bkmbigo.fundaschool.presentation.components.dialog.login.LogoutDialog
import com.github.bkmbigo.fundaschool.presentation.components.topbar.SmallTopBar
import com.github.bkmbigo.fundaschool.presentation.components.news.HomeNewsItem
import com.github.bkmbigo.fundaschool.presentation.screen.home.HomeScreenState
import com.github.bkmbigo.fundaschool.presentation.theme.FundASchoolTheme
import com.github.bkmbigo.fundaschool.presentation.theme.layoutproperties.LocalLayoutProperty
import com.github.bkmbigo.fundaschool.presentation.utils.FormFactor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun HomeScreenContent(
    state: HomeScreenState,
    onSearch: (String) -> Unit
) {
    val layoutProperties = LocalLayoutProperty.current

    val sectionTitlePadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp)
    val sectionListPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)

    // State Variables
    var isOptionsDialogOpen by remember { mutableStateOf(false) }

    var showLoginDialog by remember { mutableStateOf(false) }
    var showLogoutDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 4.dp)
            .verticalScroll(rememberScrollState())
    ) {
        SmallTopBar(
            modifier = Modifier.fillMaxWidth(),
            onSearch = onSearch,
            onOpenDialog = { isOptionsDialogOpen = true }
        )

        Spacer(modifier = Modifier.height(4.dp))

        AnimatedVisibility(
            visible = state.bookmarks.isNotEmpty(),
        ) {
            Column {
                Text(
                    text = "Bookmarks",
                    style = layoutProperties.TextStyle.sectionTitle,
                    modifier = Modifier.padding(sectionTitlePadding)
                )


            }
        }

        AnimatedVisibility(
            visible = state.news.isNotEmpty()
        ) {
            Column {
                Text(
                    text = "News",
                    style = layoutProperties.TextStyle.sectionTitle,
                    modifier = Modifier.padding(sectionTitlePadding)
                )

                if (layoutProperties.formFactor == FormFactor.PORTRAIT) {
                    val pagerState = rememberPagerState()

                    HorizontalPager(
                        pageCount = state.news.size,
                        state = pagerState,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth().padding(sectionListPadding)
                    ) { index ->
                        HomeNewsItem(
                            news = state.news[index],
                            size = DpSize(Dp.Infinity, 200.dp),
                            modifier = Modifier.fillMaxWidth().padding(sectionListPadding),
                            contentScale = ContentScale.Crop,
                            onOpenProject = { /*TODO*/ },
                            onOpenNews = { /*TODO*/ }
                        )
                    }
                } else {
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 4.dp, vertical = 4.dp),
                        contentPadding = PaddingValues(horizontal = 8.dp)
                    ) {
                        items(state.news) { newsItem ->
                            HomeNewsItem(
                                news = newsItem,
                                size = DpSize(Dp.Infinity, 200.dp),
                                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp, horizontal = 4.dp),
                                contentScale = ContentScale.Crop,
                                onOpenProject = { /*TODO*/ },
                                onOpenNews = { /*TODO*/ }
                            )
                        }
                    }
                }
            }

        }

        AnimatedVisibility(
            visible = state.featuredProjects.isNotEmpty()
        ) {
            Column {
                Text(
                    text = "Featured Project",
                    style = layoutProperties.TextStyle.sectionTitle,
                    modifier = Modifier.padding(sectionTitlePadding)
                )
            }
        }
    }

    AnimatedVisibility(
        visible = isOptionsDialogOpen || showLoginDialog || showLogoutDialog,
        enter = expandHorizontally(
            spring(stiffness = Spring.StiffnessMediumLow),
            expandFrom = Alignment.CenterHorizontally
        ) + expandVertically(
            spring(stiffness = Spring.StiffnessVeryLow),
            expandFrom = Alignment.CenterVertically,
            initialHeight = { it / 8 }
        )
    ) {
        if(isOptionsDialogOpen) {
            Dialog(
                onDismissRequest = {
                    isOptionsDialogOpen = false
                }
            ) {
                HomeActionDialog(
                    onAction = { action ->
                        when (action) {
                            HomeActionDialogAction.NavigateToAboutUs -> {}
                            HomeActionDialogAction.NavigateToAdmin -> {}
                            HomeActionDialogAction.NavigateToDonation -> {}
                            HomeActionDialogAction.NavigateToHome -> {}
                            HomeActionDialogAction.NavigateToProjects -> {}
                            HomeActionDialogAction.EditProfile -> {}
                            HomeActionDialogAction.Login -> { showLoginDialog = true }
                            HomeActionDialogAction.Logout -> { showLogoutDialog = true }
                        }
                    },
                    onDismissRequest = { isOptionsDialogOpen = false }
                )
            }
        }

        if(showLoginDialog) {
            Dialog(
                onDismissRequest = { showLoginDialog = false },
                properties = DialogProperties(usePlatformDefaultWidth = false)
            ) {
                LoginDialog(
                    onDismissRequest = { showLoginDialog = false }
                )
            }
        }

        if(showLogoutDialog) {
            Dialog(
                onDismissRequest = { showLoginDialog = false }
            ) {
                LogoutDialog(
                    onDismissRequest = { showLogoutDialog = false }
                )
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
private fun PreviewHomeScreenContent() {

    val state = HomeScreenState(
        news = MutableList(5) { index ->
            News(
                id = "redd",
                title = "President visits project in Northern Kenya",
                caption = "The President took a visit to Kaptowet Village to launch a new borehole",
                category = NewsCategory.GENERAL_NEWS,
                uploadDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
                text = "",
                author = "",
                associatedProject = null,
                media = listOf(
                    Media(
                        id = "wess",
                        mediaType = MediaType.IMAGE,
                        url = "https://images.unsplash.com/flagged/photo-1579133311477-9121405c78dd?ixlib=rb-4.0.3"
                    )
                )
            )
        },

        )

    FundASchoolTheme(formFactorState = MutableStateFlow(FormFactor.PORTRAIT)) {
        Scaffold {
            HomeScreenContent(state, {})
        }
    }
}