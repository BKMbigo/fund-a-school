package com.github.bkmbigo.fundaschool.presentation.utils

actual enum class FormFactor {
    PORTRAIT,
    LANDSCAPE;

    actual companion object {
        actual val Default = PORTRAIT
    }
}