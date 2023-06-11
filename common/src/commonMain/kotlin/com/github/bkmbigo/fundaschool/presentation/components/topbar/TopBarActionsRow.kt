package com.github.bkmbigo.fundaschool.presentation.components.topbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.IntrinsicMeasurable
import androidx.compose.ui.layout.IntrinsicMeasureScope
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.unit.Constraints
import com.github.bkmbigo.fundaschool.presentation.theme.layoutproperties.LocalLayoutProperty

// Hopefully animations will be added later :)
/**
 * A row that adds buttons whenever they can fit in the view (had thought of using a custom layout but an error is generated on web)
 */
@Composable
internal fun TopBarActionsRow(
    userIsAdmin: Boolean,
    onAction: (AdaptiveHomeTopBarAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    val layoutProperties = LocalLayoutProperty.current

    Layout(
        modifier = modifier,
        content = {
            TextButton(
                onClick = {
                    onAction(AdaptiveHomeTopBarAction.NavigateToDonationsScreen)
                },
                shape = RectangleShape,
                modifier = Modifier.fillMaxHeight()
            ) {
                Text(
                    text = "Donation",
                    style = layoutProperties.TextStyle.actionsTitle
                )
            }

            TextButton(
                onClick = {
                    onAction(AdaptiveHomeTopBarAction.NavigateToProjectsScreen)
                },
                shape = RectangleShape,
                modifier = Modifier.fillMaxHeight()
            ) {
                Text(
                    text = "Projects",
                    style = layoutProperties.TextStyle.actionsTitle
                )
            }

            if (userIsAdmin) {
                TextButton(
                    onClick = {
                        onAction(AdaptiveHomeTopBarAction.NavigateToAdminScreen)
                    },
                    shape = RectangleShape,
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Text(
                        text = "Admin Panel",
                        style = layoutProperties.TextStyle.actionsTitle
                    )
                }
            } else {
                TextButton(
                    onClick = {
                        onAction(AdaptiveHomeTopBarAction.NavigateToAboutUsScreen)
                    },
                    shape = RectangleShape,
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Text(
                        text = "About Us",
                        style = layoutProperties.TextStyle.actionsTitle
                    )
                }
            }
        },
        measurePolicy = object : MeasurePolicy {
            override fun MeasureScope.measure(
                measurables: List<Measurable>,
                constraints: Constraints
            ): MeasureResult {
                // Get measurables
                val donationButtonMeasurable = measurables[0]
                val projectsButtonMeasurable = measurables[1]
                val adminButtonMeasurable = measurables[2]

                // Measure their sizes
                val donationButtonPlaceable = donationButtonMeasurable.measure(
                    Constraints.fixed(
                        width = donationButtonMeasurable.maxIntrinsicWidth(constraints.maxHeight),
                        height = constraints.maxHeight
                    )
                )
                val projectsButtonPlaceable = projectsButtonMeasurable.measure(
                    Constraints.fixed(
                        width = projectsButtonMeasurable.maxIntrinsicWidth(constraints.maxHeight),
                        height = constraints.maxHeight
                    )
                )
                val adminButtonPlaceable = adminButtonMeasurable.measure(
                    Constraints.fixed(
                        width = adminButtonMeasurable.maxIntrinsicWidth(constraints.maxHeight),
                        height = constraints.maxHeight
                    )
                )


                val showDonation =
                    constraints.maxWidth > donationButtonPlaceable.width
                val showProject =
                    constraints.maxWidth > donationButtonPlaceable.width + projectsButtonPlaceable.width
                val showAdmin =
                    constraints.maxWidth > donationButtonPlaceable.width + projectsButtonPlaceable.width + adminButtonPlaceable.width

                return layout(constraints.maxWidth, constraints.maxHeight) {
                    if (showDonation) {
                        donationButtonPlaceable.placeRelative(
                            x = constraints.maxWidth - donationButtonPlaceable.width -
                                    if(showProject) projectsButtonPlaceable.width else 0 -
                                    if(showAdmin) adminButtonPlaceable.width else 0,
                            0
                        )
                    }
                    if (showProject) {
                        projectsButtonPlaceable.placeRelative(
                            x = constraints.maxWidth -
                                    projectsButtonPlaceable.width -
                                    if(showAdmin) adminButtonPlaceable.width else 0,
                            0
                        )
                    }
                    if (showAdmin) {
                        adminButtonPlaceable.placeRelative(
                            x = constraints.maxWidth - adminButtonPlaceable.width,
                            0
                        )
                    }
                }
            }

            override fun IntrinsicMeasureScope.minIntrinsicHeight(
                measurables: List<IntrinsicMeasurable>,
                width: Int
            ): Int  = measurables.maxOfOrNull { it.minIntrinsicHeight(width) } ?: 0
        }
    )
}
