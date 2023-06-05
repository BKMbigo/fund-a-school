package com.github.bkmbigo.fundaschool.android

import android.app.Activity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.github.bkmbigo.fundaschool.domain.models.firebase.Project
import com.github.bkmbigo.fundaschool.presentation.screen.home.HomeScreenAction
import com.github.bkmbigo.fundaschool.presentation.screens.project.ProjectScreen
import com.github.bkmbigo.fundaschool.presentation.theme.FundASchoolTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.datetime.LocalDate
import sqip.CardEntry

class MainActivity : AppCompatActivity() {

    private val _currentCardNumber = MutableStateFlow<String?>(null)
    val currentCardNumber = _currentCardNumber.asStateFlow()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FundASchoolTheme {
                FakeScreen(project = Project(
                    id = "wqwq",
                    name = "Namsawas",
                    description = "Scasaasnasnvbdjbvjkbvdvjksdvsdjvndfnvkld",
                    featured = true,
                    schools = "Likaisksal asaasa",
                    startDate = 15233,
                    completionDate = 1588,
                    targetAmount = 45_000.0f,
                    currentAmount = 0.0f,
                    donors = 0,
                    mediaUrl = ""
                ), onAction = {

                }

                )
            }
        }
    }
}


@Composable
fun FakeScreen(
    project: Project,
    onAction: (HomeScreenAction) -> Unit
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Button(
            onClick = {
                CardEntry.startCardEntryActivity(context as Activity)
            }
        ) {
            Text(
                text = "Pay"
            )
        }
    }
}
