package com.github.bkmbigo.fundaschool.presentation.theme

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.platform.Font
import com.github.bkmbigo.fundaschool.presentation.utils.LocalFont
import com.github.bkmbigo.fundaschool.presentation.utils.LocalFontFamily
import com.github.bkmbigo.fundaschool.presentation.utils.getBytesProvider
import io.ktor.client.*
import io.ktor.client.engine.js.*

private val resourcePath = "assets/font/"

actual suspend fun generateExoFontFamily(): FontFamily {
    val client = HttpClient(Js)

    val fonts = LocalFontFamily.EXO.fonts.map {
        Font(
            identity = it.fontName,
            data = client.getFont(it),
            weight = it.fontWeight,
            style = it.fontStyle
        )
    }

    return FontFamily(fonts)
}

actual suspend fun generateRobotoSansFontFamily(): FontFamily {
    val client = HttpClient(Js)

    val fonts = LocalFontFamily.ROBOTO_SANS.fonts.map {
        Font(
            identity = it.fontName,
            data = client.getFont(it),
            weight = it.fontWeight,
            style = it.fontStyle
        )
    }

    return FontFamily(fonts)
}

private suspend fun HttpClient.getFont(font: LocalFont): ByteArray {

    return getBytesProvider("" + resourcePath + font.fileName)
//    val typeFace = Typeface.makeFromData(Data.makeFromBytes(bytes))
//    return Typeface(typeFace)
}