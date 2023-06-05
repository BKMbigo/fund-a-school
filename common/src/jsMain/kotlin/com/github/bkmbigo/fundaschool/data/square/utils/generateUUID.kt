package com.github.bkmbigo.fundaschool.data.square.utils

import kotlinx.uuid.SecureRandom
import kotlinx.uuid.UUID
import kotlinx.uuid.generateUUID

actual fun generateUUID(): String = UUID.generateUUID(SecureRandom).toString(false)