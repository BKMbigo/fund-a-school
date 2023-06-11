package com.github.bkmbigo.fundaschool.presentation.screens.donations

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.github.bkmbigo.fundaschool.presentation.components.donations.DonationList
import com.github.bkmbigo.fundaschool.presentation.theme.layoutproperties.LocalLayoutProperty

@Composable
fun SmallDonationsScreenContent(
    state: DonationsScreenState,
    onAction: (DonationsScreenAction) -> Unit
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
                    onClick = { onAction(DonationsScreenAction.NavigateUp) },
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ChevronLeft,
                        contentDescription = null
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                Text(
                    text = "Donations",
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    style = layoutProperties.TextStyle.pageTitle
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Top Donations",
                    style = layoutProperties.TextStyle.sectionTitle,
                    modifier = Modifier.padding(vertical = 2.dp)
                )

                DonationList(
                    donations = state.topDonations,
                    title = {
                        Text(
                            text = "Top Donors",
                            style = layoutProperties.TextStyle.sectionTitle,
                        )
                    },
                    size = DpSize.Unspecified,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .padding(vertical  = 2.dp)
                )

                Divider(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp, horizontal = 8.dp))

                DonationList(
                    donations = state.latestDonations,
                    title = {
                        Text(
                            text = "Latest Donors",
                            style = layoutProperties.TextStyle.sectionTitle,
                        )
                    },
                    size = DpSize.Unspecified,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .padding(vertical  = 2.dp)
                )

                /* TODO: Add Donor Lists */
            }
        }
    }


}