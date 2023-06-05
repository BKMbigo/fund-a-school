package com.github.bkmbigo.fundaschool.android

import android.app.Application
import com.github.bkmbigo.fundaschool.di.commonModule
import com.github.bkmbigo.fundaschool.di.expectedModule
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class FundASchoolApplication: Application() {

    private val _currentCardNumber = MutableStateFlow<String?>(null)
    val currentCardNumber = _currentCardNumber.asStateFlow()

    fun setCurrentCardNonce(nonce: String?) {
        _currentCardNumber.value = nonce
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@FundASchoolApplication)
            modules(commonModule, expectedModule)
        }
    }

}