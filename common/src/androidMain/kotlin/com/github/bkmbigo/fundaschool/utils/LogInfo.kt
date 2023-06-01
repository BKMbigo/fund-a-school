package com.github.bkmbigo.fundaschool.utils

import android.util.Log

actual fun LogInfo(message: String) {
    Log.i("Application Logger", "LogInfo: $message")
}