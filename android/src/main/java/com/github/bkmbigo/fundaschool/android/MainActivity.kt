package com.github.bkmbigo.fundaschool.android

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import cafe.adriel.voyager.navigator.Navigator
import com.github.bkmbigo.fundaschool.di.withKoin
import com.github.bkmbigo.fundaschool.domain.repositories.square.SubscriptionCadence
import com.github.bkmbigo.fundaschool.domain.repositories.square.SubscriptionsRepository
import com.github.bkmbigo.fundaschool.presentation.screens.home.HomeScreen
import com.github.bkmbigo.fundaschool.presentation.theme.FundASchoolTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val _currentCardNumber = MutableStateFlow<String?>(null)
    val currentCardNumber = _currentCardNumber.asStateFlow()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FundASchoolTheme {
                Navigator(HomeScreen())
            }
        }
    }
}