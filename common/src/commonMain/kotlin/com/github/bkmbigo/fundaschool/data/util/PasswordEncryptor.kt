package com.github.bkmbigo.fundaschool.data.util

expect object PasswordEncryptor {
    fun encryptPassword(password: String): String
    fun confirmPassword(password: String, hash: String): Boolean
}