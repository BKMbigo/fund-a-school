package com.github.bkmbigo.fundaschool.presentation.utils

enum class LocalFontFamily(val fonts: List<LocalFont>) {
    EXO(
        listOf(
            EXO2_BOLD,
            EXO2_BOLD_ITALIC,
            EXO2_MEDIUM,
            EXO2_MEDIUM_ITALIC,
            EXO2_REGULAR,
            EXO2_SEMI_BOLD,
            EXO2_SEMI_BOLD_ITALIC
        )
    ),
    ROBOTO_SANS(
        listOf(
            ROBOTO_SLAB_BLACK,
            ROBOTO_SLAB_BOLD,
            ROBOTO_SLAB_LIGHT,
            ROBOTO_SLAB_MEDIUM,
            ROBOTO_SLAB_REGULAR,
            ROBOTO_SLAB_SEMI_BOLD,
            ROBOTO_SLAB_THIN,
            ROBOTO_SLAB_EXTRA_LIGHT,
            ROBOTO_SLAB_EXTRA_BOLD
        )
    )
}