package com.github.bkmbigo.fundaschool.presentation.components.donations

import com.github.bkmbigo.fundaschool.domain.models.firebase.Project
import com.github.bkmbigo.fundaschool.domain.models.firebase.User

data class DonationListItem(
    val index: Int,
    val user: User,
    val project: Project,
    val amount: Float
)
