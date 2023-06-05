package com.github.bkmbigo.fundaschool.data.square.utils

import java.util.UUID

actual fun generateUUID(): String = UUID.randomUUID().toString()