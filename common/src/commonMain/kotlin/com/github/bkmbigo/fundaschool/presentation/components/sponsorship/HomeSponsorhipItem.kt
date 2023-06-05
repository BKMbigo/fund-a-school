package com.github.bkmbigo.fundaschool.presentation.components.sponsorship

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.interaction.HoverInteraction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Subscriptions
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.github.bkmbigo.fundaschool.domain.models.firebase.Sponsorship
import com.github.bkmbigo.fundaschool.presentation.components.news.MediaImageView
import com.github.bkmbigo.fundaschool.presentation.components.project.SponsorshipDonationBar
import com.github.bkmbigo.fundaschool.presentation.theme.layoutproperties.LocalLayoutProperty

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun homeSponsorshipItem(
    sponsorship: Sponsorship,
    modifier: Modifier = Modifier,
    onSponsorshipOpened: () -> Unit,
    onSubscribe: () -> Unit,
    onDonate: () -> Unit,
) {
    val layoutProperties = LocalLayoutProperty.current

    val cardInteractionSource = remember { MutableInteractionSource() }

    var isButtonVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        cardInteractionSource.interactions.collect { interaction ->
            when (interaction) {
                is HoverInteraction.Enter -> {
                    isButtonVisible = true
                }

                is HoverInteraction.Exit -> {
                    isButtonVisible = false
                }
            }
        }
    }

    ElevatedCard(
        onClick = onSponsorshipOpened,
        modifier = modifier,
        interactionSource = cardInteractionSource
    ) {
        if (sponsorship.mediaUrl.isNotBlank()) {
            MediaImageView(
                mediaUrl = sponsorship.mediaUrl,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f, true),
                contentScale = ContentScale.FillHeight
            )
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f, true),
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f, true)
                .padding(horizontal = 8.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = sponsorship.title,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                style = layoutProperties.TextStyle.informationTitle
            )

            Spacer(modifier = Modifier.height(4.dp))

            InformationRow(
                title = "Active Sponsors",
                value = sponsorship.activeSponsors.toString(),
                modifier = Modifier.fillMaxWidth()
            )

            Divider(modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 4.dp))

            InformationRow(
                title = "Current Contributions",
                value = sponsorship.currentAmount.toString(),
                modifier = Modifier.fillMaxWidth()
            )

            Divider(modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 4.dp))

            InformationRow(
                title = "Target Amount",
                value = sponsorship.targetAmount.toString(),
                modifier = Modifier.fillMaxWidth()
            )

            Divider(modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 4.dp))

            SponsorshipDonationBar(
                sponsorship = sponsorship,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp, vertical = 2.dp),
            )

            AnimatedVisibility(
                visible = isButtonVisible,
                enter = expandVertically(
                    animationSpec = spring(
                        stiffness = Spring.StiffnessVeryLow
                    ),
                    expandFrom = Alignment.Bottom
                ),
                exit = shrinkVertically(
                    animationSpec = spring(
                        stiffness = Spring.StiffnessMediumLow
                    ),
                    shrinkTowards = Alignment.Bottom
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Box(
                        modifier = Modifier.weight(1f, true)
                    )

                    Button(
                        onClick = onSubscribe
                    ) {
                        Icon(
                            imageVector = Icons.Default.Subscriptions,
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(
                            text = "Subscribe"
                        )
                    }

                    Spacer(modifier = Modifier.width(4.dp))

                    Button(
                        onClick = onDonate
                    ) {
                        Icon(
                            imageVector = Icons.Default.Subscriptions,
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(
                            text = "Donate"
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(4.dp))
        }
    }

}

@Composable
private fun InformationRow(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    val layoutProperties = LocalLayoutProperty.current

    Row(
        modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = layoutProperties.TextStyle.informationEmphasis
        )

        Text(
            text = value,
            style = layoutProperties.TextStyle.informationText
        )
    }
}