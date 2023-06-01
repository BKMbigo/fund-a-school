package com.github.bkmbigo.fundaschool.presentation.screen.admin

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.github.bkmbigo.fundaschool.domain.models.News
import com.github.bkmbigo.fundaschool.presentation.components.admin.OverviewItem
import com.github.bkmbigo.fundaschool.presentation.components.list.HorizontalPagerList
import com.github.bkmbigo.fundaschool.presentation.components.news.HomeNewsItem
import com.github.bkmbigo.fundaschool.presentation.components.section.SectionHeader
import com.github.bkmbigo.fundaschool.presentation.theme.layoutproperties.LocalLayoutProperty

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SmallAdminScreenContent(
    state: AdminScreenState,
    onAction: (AdminScreenAction) -> Unit
) {
    val layoutProperties = LocalLayoutProperty.current

    Column {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                IconButton(
                    onClick = { onAction(AdminScreenAction.NavigateUp) },
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ChevronLeft,
                        contentDescription = null
                    )
                }
            }

            Column(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
            ) {
                Text(
                    text = "Admin Panel",
                    modifier = Modifier.align(Alignment.CenterHorizontally).padding(vertical = 4.dp),
                    style = layoutProperties.TextStyle.pageTitle
                )
            }

            LazyHorizontalGrid(
                rows = GridCells.Fixed(2),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 4.dp),
                verticalArrangement = Arrangement.Center,
                horizontalArrangement = Arrangement.Center
            ) {
                item {
                    OverviewItem(
                        title = "Completed Projects",
                        value = state.completedProjects.toString(),
                        size = DpSize(Dp.Infinity, Dp.Unspecified),
                        modifier = Modifier.animateItemPlacement()
                    )
                }

                item {
                    OverviewItem(
                        title = "Ongoing Projects",
                        value = state.ongoingProjects.toString(),
                        size = DpSize(Dp.Infinity, Dp.Unspecified),
                        modifier = Modifier.animateItemPlacement()
                    )
                }
                item {
                    OverviewItem(
                        title = "Total Donations",
                        value = state.totalDonations.toString(),
                        size = DpSize(Dp.Infinity, Dp.Unspecified),
                        modifier = Modifier.animateItemPlacement()
                    )
                }
                item {
                    OverviewItem(
                        title = "Total Donors",
                        value = state.totalDonors.toString(),
                        size = DpSize(Dp.Infinity, Dp.Unspecified),
                        modifier = Modifier.animateItemPlacement()
                    )
                }
            }

            SectionHeader(
                title = "News",
                onSearch = { onAction(AdminScreenAction.SearchNews(it)) },
                onAdd = { onAction(AdminScreenAction.AddNews) },
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                isAdmin = state.isAdmin
            )

            HorizontalPagerList(
                pageCount = state.news.size,
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                pagerContent = { index ->
                    HomeNewsItem(
                        news = state.news[index],
                        size = DpSize(Dp.Infinity, Dp.Unspecified),
                        modifier = Modifier,
                        contentScale = ContentScale.Crop,
                        onOpenProject = {
                            state.news[index].associatedProject?.let {
                                onAction(AdminScreenAction.OpenProject(it))
                            }
                        },
                        onOpenNews = {
                            onAction(AdminScreenAction.OpenNews(state.news[index]))
                        }
                    )
                },
                listContent = {
                    items(state.news) { newsItem ->
                        HomeNewsItem(
                            news = newsItem,
                            size = DpSize(Dp.Unspecified, Dp.Unspecified),
                            modifier = Modifier,
                            contentScale = ContentScale.Crop,
                            onOpenProject = {
                                newsItem.associatedProject?.let {
                                    onAction(AdminScreenAction.OpenProject(it))
                                }
                            },
                            onOpenNews = {
                                onAction(AdminScreenAction.OpenNews(newsItem))
                            }
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(4.dp))

            SectionHeader(
                title = "Projects",
                onSearch = { onAction(AdminScreenAction.SearchNews(it)) },
                onAdd = { onAction(AdminScreenAction.AddNews) },
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                isAdmin = state.isAdmin
            )
        }
    }
}