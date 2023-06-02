package com.github.bkmbigo.fundaschool.presentation.components.admin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.github.bkmbigo.fundaschool.presentation.theme.layoutproperties.LocalLayoutProperty
import com.github.bkmbigo.fundaschool.presentation.utils.applyCustomSize

@Composable
fun OverviewItem(
    title: String,
    value: String,
    size: DpSize,
    modifier: Modifier = Modifier
) {
    val layoutProperties = LocalLayoutProperty.current

    Card(
        modifier = modifier.applyCustomSize(size),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(
            text = title,
            style = layoutProperties.TextStyle.informationTitle,
            modifier = Modifier.align(Alignment.CenterHorizontally).padding(horizontal = 4.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = value,
                modifier = Modifier.align(Alignment.Center).padding(vertical = 8.dp),
                style = layoutProperties.TextStyle.applicationTitle
            )
        }
    }
}