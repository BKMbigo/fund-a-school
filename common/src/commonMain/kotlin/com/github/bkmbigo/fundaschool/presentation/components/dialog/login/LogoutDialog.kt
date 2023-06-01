package com.github.bkmbigo.fundaschool.presentation.components.dialog.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.github.bkmbigo.fundaschool.di.withKoin
import com.github.bkmbigo.fundaschool.presentation.components.dialog.DialogLayout
import com.github.bkmbigo.fundaschool.presentation.theme.layoutproperties.LocalLayoutProperty
import kotlinx.coroutines.launch

@Composable
fun LogoutDialog(
    onDismissRequest: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val layoutPresenter = LocalLayoutProperty.current

    val presenter = remember { withKoin<LogoutPresenter>() }

    DialogLayout(onDismissRequest = onDismissRequest) {
        Text(
            text = "Logout",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = layoutPresenter.TextStyle.sectionTitle
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Are you sure you want to logout from the application?",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = layoutPresenter.TextStyle.pageSubTitle
        )

        Row(
           modifier = Modifier.fillMaxWidth(),
        ) {
            Box(
                modifier = Modifier.weight(1f, true)
            )

            TextButton(
                onClick = onDismissRequest
            ) {
                Text(text = "No")
            }

            TextButton(
                onClick = {
                    coroutineScope.launch {
                        presenter.logoutUser()
                    }
                }
            ) {
                Text(text = "Yes")
            }
        }
    }
}