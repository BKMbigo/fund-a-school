package com.github.bkmbigo.fundaschool.di

import org.koin.core.component.KoinComponent
import org.koin.core.component.get


val withKoin = WithKoin()

// Koin Component
class WithKoin : KoinComponent {
    inline operator fun <reified T : Any> invoke(): T = get()
}