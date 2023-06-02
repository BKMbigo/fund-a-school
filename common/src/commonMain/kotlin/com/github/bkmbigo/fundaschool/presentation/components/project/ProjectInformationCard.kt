package com.github.bkmbigo.fundaschool.presentation.components.project

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.github.bkmbigo.fundaschool.domain.models.Project
import com.github.bkmbigo.fundaschool.presentation.theme.layoutproperties.LocalLayoutProperty
import com.github.bkmbigo.fundaschool.presentation.utils.applyCustomSize

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectInformationCard(
    project: Project,
    targetAmount: TextFieldValue,
    onTargetAmountChanged: (TextFieldValue) -> Unit,
    size: DpSize,
    modifier: Modifier = Modifier,
    isEditing: Boolean = false,
) {
    val layoutProperties = LocalLayoutProperty.current

    OutlinedCard(
        modifier = modifier
            .applyCustomSize(size)
            .clip(RoundedCornerShape(12.dp))
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
            ) {
                Text(
                    text = "Target Amount",
                    style = layoutProperties.TextStyle.bodyEmphasis
                )

                Box(
                    modifier = Modifier.weight(1f, true)
                )

                if (!isEditing) {
                   Text(
                       text = targetAmount.text,
                       style = layoutProperties.TextStyle.bodyText
                   )
                } else {
                    OutlinedTextField(
                        value = targetAmount,
                        onValueChange = { value ->
                            if(value.text.all { it.isDigit() || it == '.' }) {
                                onTargetAmountChanged(value)
                            } /*TODO: Tell user when is not allowed*/
                        },
                        textStyle = layoutProperties.TextStyle.bodyText,
                        placeholder = {
                            Text("Target Amount")
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                    )
                }
            }

            Divider(modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp))

            InformationRow(
                title = "Donors",
                value = project.donors.toString(),
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
            )

            InformationRow(
                title = "Total Contributions",
                value = project.currentAmount.toString(),
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
            )

            Divider(modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp))


            InformationRow(
                title = "Remaining Contributions",
                value = "${project.targetAmount - project.currentAmount}",
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
            )
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

    Row(modifier = modifier) {
        Text(
            text = title,
            style = layoutProperties.TextStyle.bodyEmphasis
        )

        Box(
            modifier = Modifier.weight(1f, true)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = value,
            style = layoutProperties.TextStyle.bodyText
        )
    }
}