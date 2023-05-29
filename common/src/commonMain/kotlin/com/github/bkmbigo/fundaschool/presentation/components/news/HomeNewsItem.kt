package com.github.bkmbigo.fundaschool.presentation.components.news

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.HoverInteraction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.bkmbigo.fundaschool.domain.models.News
import com.github.bkmbigo.fundaschool.domain.models.Project
import com.seiko.imageloader.rememberAsyncImagePainter
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeNewsItem(
    news: News,
    modifier: Modifier = Modifier,
    onOpenProject: (Project) -> Unit,
    onOpenNews: (News) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val cardInteractionSource = remember { MutableInteractionSource() }

    var isExpanded by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        cardInteractionSource.interactions.collect { interaction ->
            if (interaction is HoverInteraction) {
                when (interaction) {
                    is HoverInteraction.Enter -> {
                        isExpanded = true
                    }

                    is HoverInteraction.Exit -> {
                        isExpanded = false
                    }
                }
            }
        }
    }

    Column(
        modifier = modifier
            .shadow(4.dp, shape = RoundedCornerShape(8.dp))
            .background(
                MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(8.dp)
            )
            .clip(RoundedCornerShape(8.dp))
            .hoverable(
                interactionSource = cardInteractionSource,
                enabled = true
            )
            .clickable(
                cardInteractionSource,
                LocalIndication.current,
                true
            ) {
              onOpenNews(news)
            },
    ) {
        Image(
            rememberAsyncImagePainter("https://corsproxy.io/?https://images.unsplash.com/photo-1473649085228-583485e6e4d7?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1032&q=80"),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().weight(1f, true),
            contentScale = ContentScale.FillWidth
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProvideTextStyle(MaterialTheme.typography.titleMedium) {
                Text(
                    text = news.title,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(
                            horizontal = 12.dp,
                            vertical = 12.dp
                        ),
                    textAlign = TextAlign.Center,
                )
            }

            AnimatedVisibility(
                visible = isExpanded,
                enter = expandVertically(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessVeryLow
                    )
                ),
                exit = shrinkVertically(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp)
                ) {
                    Divider(modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp))

                    ProvideTextStyle(MaterialTheme.typography.bodyMedium) {
                        Text(
                            text = news.caption,
                            modifier = Modifier.padding(top = 4.dp, bottom = 8.dp),
                            overflow = TextOverflow.Ellipsis,
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        SuggestionChip(
                            onClick = {
                                news.associatedProject?.let { onOpenProject(it) }
                            },
                            label = {
                                Icon(
                                    imageVector = Icons.Default.OpenInNew,
                                    contentDescription = null
                                )

                                Spacer(modifier = Modifier.width(4.dp))

                                Text(
                                    text = "Open Project"
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}