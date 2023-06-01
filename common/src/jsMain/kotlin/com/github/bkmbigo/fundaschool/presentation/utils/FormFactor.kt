package com.github.bkmbigo.fundaschool.presentation.utils

actual enum class FormFactor {
    SMALL,
    LARGE;

    actual companion object {
        actual val Default = SMALL
    }
}