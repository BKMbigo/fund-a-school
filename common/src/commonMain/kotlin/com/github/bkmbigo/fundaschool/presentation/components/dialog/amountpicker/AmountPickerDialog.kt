package com.github.bkmbigo.fundaschool.presentation.components.dialog.amountpicker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.github.bkmbigo.fundaschool.presentation.components.dialog.DialogLayout
import com.github.bkmbigo.fundaschool.presentation.theme.layoutproperties.LocalLayoutProperty

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmountPickerDialog(
    onDismissRequest: () -> Unit,
    onAmountPicked: (Float) -> Unit,
) {
    val layoutProperties = LocalLayoutProperty.current

    var inputValue by remember{ mutableStateOf(0f) }

    DialogLayout(
        onDismissRequest = onDismissRequest
    ) {
        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Thank you for choosing to donate towards the programme. To continue, please enter the amount on the form below and await redirection to enter your card details"
        )

        Text(
            text = "Amount",
            style = layoutProperties.TextStyle.textLayoutHelper
        )

        OutlinedTextField(
            value = inputValue.toString(),
            onValueChange = {
                inputValue = it.toFloatOrNull() ?: inputValue

            },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {

            TextButton(
                onClick = onDismissRequest
            ) {
                Text(
                    text = "Cancel"
                )
            }

            Spacer(Modifier.width(4.dp))

            Button(
                onClick = { onAmountPicked(inputValue) }
            ) {
                Text("Save")
            }
        }

        Spacer(modifier = Modifier.height(4.dp))
    }
}