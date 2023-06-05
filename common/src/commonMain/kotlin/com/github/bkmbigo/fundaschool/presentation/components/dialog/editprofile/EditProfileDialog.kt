package com.github.bkmbigo.fundaschool.presentation.components.dialog.editprofile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NoAccounts
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.github.bkmbigo.fundaschool.domain.models.firebase.User
import com.github.bkmbigo.fundaschool.presentation.components.dialog.DialogLayout
import com.github.bkmbigo.fundaschool.presentation.components.news.MediaImageView
import com.github.bkmbigo.fundaschool.presentation.theme.layoutproperties.LocalLayoutProperty

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileDialog(
    user: User,
    onDismissRequest: () -> Unit,
) {
    val layoutProperties = LocalLayoutProperty.current

    var displayName by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(""))
    }

    DialogLayout(
        onDismissRequest = onDismissRequest
    ) {

        Box(
            modifier = Modifier
                .size(70.dp)
                .padding(vertical = 12.dp)
                .align(Alignment.CenterHorizontally)
                .shadow(4.dp, RoundedCornerShape(50.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .clip(RoundedCornerShape(50.dp))
        ) {
            if (user.photoUrl == null) {
                Icon(
                    imageVector = Icons.Default.NoAccounts,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                MediaImageView(
                    mediaUrl = user.photoUrl,
                    contentScale = ContentScale.Crop
                )
            }
        }

        Text(
            text = "Display Name",
            style = layoutProperties.TextStyle.textLayoutHelper
        )
        OutlinedTextField(
            value = displayName,
            onValueChange = {
                displayName = it
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                //textColor = MaterialTheme.colorScheme.onSurfaceVariant
            )
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

            Spacer(modifier = Modifier.width(4.dp))

            Button(
                onClick = {

                }
            ) {
                Text(
                    text = "Save"
                )
            }
        }
    }

}