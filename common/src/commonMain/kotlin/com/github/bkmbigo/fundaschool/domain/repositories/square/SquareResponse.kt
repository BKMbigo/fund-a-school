package com.github.bkmbigo.fundaschool.domain.repositories.square

import com.github.bkmbigo.fundaschool.data.square.models.SquareError

sealed class SquareResponse<T> {
    data class Success<T>(val data: T): SquareResponse<T>()
    data class Error<T>(val error: List<SquareError>): SquareResponse<T>()
}
