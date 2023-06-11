package com.github.bkmbigo.fundaschool.presentation.screens.admin

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
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

            Text(
                text = "Admin Panel",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = layoutProperties.TextStyle.pageTitle
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
            ) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Layout(
                            modifier = Modifier
                                .padding(horizontal = 8.dp, vertical = 2.dp),
                            content = {
                                OverviewItem(
                                    title = "Completed Projects",
                                    value = state.completedProjects.toString(),
                                    size = DpSize.Unspecified,
                                    modifier = Modifier
                                        .padding(4.dp)
                                )

                                OverviewItem(
                                    title = "Ongoing Projects",
                                    value = state.ongoingProjects.toString(),
                                    size = DpSize.Unspecified,
                                    modifier = Modifier.padding(4.dp)
                                )

                                OverviewItem(
                                    title = "Total Donations",
                                    value = state.totalDonations.toString(),
                                    size = DpSize.Unspecified,
                                    modifier = Modifier.padding(4.dp)
                                )

                                OverviewItem(
                                    title = "Total Donors",
                                    value = state.totalDonors.toString(),
                                    size = DpSize.Unspecified,
                                    modifier = Modifier.padding(4.dp)
                                )
                            }
                        ) { measureables, constraints ->
                            val minimumIntrinsicHeight =
                                measureables.maxOf { it.maxIntrinsicHeight(constraints.maxWidth) }
                            val minimumIntrinsicWidth =
                                measureables.maxOf { it.maxIntrinsicWidth(constraints.maxHeight) }


                            val firstBox = measureables[0].measure(
                                Constraints.fixed(
                                    minimumIntrinsicWidth,
                                    minimumIntrinsicHeight
                                )
                            )
                            val secondBox = measureables[1].measure(
                                Constraints.fixed(
                                    minimumIntrinsicWidth,
                                    minimumIntrinsicHeight
                                )
                            )
                            val thirdBox = measureables[2].measure(
                                Constraints.fixed(
                                    minimumIntrinsicWidth,
                                    minimumIntrinsicHeight
                                )
                            )
                            val fourthBox = measureables[3].measure(
                                Constraints.fixed(
                                    minimumIntrinsicWidth,
                                    minimumIntrinsicHeight
                                )
                            )

                            val startOffset = (constraints.maxWidth - 2 * minimumIntrinsicWidth) / 4


                            layout(minimumIntrinsicWidth * 2, minimumIntrinsicHeight * 2) {
                                firstBox.placeRelative(0, 0)
                                secondBox.placeRelative(minimumIntrinsicWidth, 0)
                                thirdBox.placeRelative(0, minimumIntrinsicHeight)
                                fourthBox.placeRelative(
                                    minimumIntrinsicWidth,
                                    minimumIntrinsicHeight
                                )
                            }
                        }
                    }
                }

                item {
                    SectionHeader(
                        title = "News",
                        onSearch = { onAction(AdminScreenAction.SearchNews(it)) },
                        onAdd = { onAction(AdminScreenAction.AddNews) },
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                        isAdmin = state.isAdmin
                    )
                }

                item {
                    HorizontalPagerList(
                        pageCount = state.news.size,
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(horizontal = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        pagerContent = { index ->
                            HomeNewsItem(
                                news = state.news[index],
                                size = DpSize(Dp.Infinity, 200.dp),
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
                                    size = DpSize(Dp.Infinity, 200.dp),
                                    modifier = Modifier.padding(horizontal = 4.dp),
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
                }

                item { Spacer(modifier = Modifier.height(4.dp)) }

                item {
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

    }
}