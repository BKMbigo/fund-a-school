package com.github.bkmbigo.fundaschool.presentation.components.donor

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.github.bkmbigo.fundaschool.domain.models.User
import com.github.bkmbigo.fundaschool.presentation.utils.applyCustomSize

@Composable
fun DonorsList(
    donations: Map<User, Float>,
    title: @Composable RowScope.() -> Unit,
    size: DpSize,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        modifier = modifier.applyCustomSize(size),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.outlinedCardElevation(6.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp),
        ) {
            title()
        }

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(
            modifier = Modifier
                .weight(1f, true)
                .fillMaxWidth(),
        ) {
            itemsIndexed(donations.toList()) { index, donation ->
                DonorListItem(
                    index = index,
                    user = donation.first,
                    amount = donation.second,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                )
            }
        }
    }
}