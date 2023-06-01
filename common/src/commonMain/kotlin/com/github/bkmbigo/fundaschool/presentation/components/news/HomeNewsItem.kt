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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.bkmbigo.fundaschool.domain.models.News
import com.github.bkmbigo.fundaschool.domain.models.Project
import com.github.bkmbigo.fundaschool.presentation.theme.layoutproperties.LocalLayoutProperty
import com.github.bkmbigo.fundaschool.presentation.utils.applyCustomSize
import com.seiko.imageloader.rememberAsyncImagePainter
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeNewsItem(
    news: News,
    size: DpSize,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    onOpenProject: (Project) -> Unit,
    onOpenNews: (News) -> Unit
) {
    val layoutProperties = LocalLayoutProperty.current
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
            .applyCustomSize(size)
            .shadow(4.dp, shape = RoundedCornerShape(20.dp))
            .background(
                MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(20.dp)
            )
            .clip(RoundedCornerShape(20.dp))
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
        if (news.media.isNotEmpty()) {
            MediaImageView(
                media = news.media.first(),
                modifier = Modifier.weight(1f, true),
                options = MainMediaImageViewOptions(
                    contentScale = contentScale
                )
            )
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f, true)
                    .background(MaterialTheme.colorScheme.tertiaryContainer),
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .background(MaterialTheme.colorScheme.primaryContainer),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = news.title,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(
                        horizontal = 12.dp,
                        vertical = 12.dp
                    ),
                textAlign = TextAlign.Center,
                style = layoutProperties.TextStyle.informationTitle
            )

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

                    Text(
                        text = news.caption,
                        modifier = Modifier.padding(top = 4.dp, bottom = 8.dp),
                        overflow = TextOverflow.Ellipsis,
                    )

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
                            },
                            colors = SuggestionChipDefaults.elevatedSuggestionChipColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                labelColor = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        )
                    }
                }
            }
        }
    }
}