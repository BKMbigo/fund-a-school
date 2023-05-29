package com.github.bkmbigo.fundaschool.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Surface
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import com.github.bkmbigo.fundaschool.presentation.screens.home.HomeScreen
import com.github.bkmbigo.fundaschool.presentation.theme.FundASchoolTheme
import com.github.bkmbigo.fundaschool.presentation.utils.FormFactor

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FundASchoolTheme {
                Navigator(HomeScreen(FormFactor.MOBILE))
            }
        }
    }
}