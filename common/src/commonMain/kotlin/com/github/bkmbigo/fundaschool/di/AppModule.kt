package com.github.bkmbigo.fundaschool.di

import com.github.bkmbigo.fundaschool.data.repositories.*
import com.github.bkmbigo.fundaschool.domain.repositories.*
import com.github.bkmbigo.fundaschool.presentation.components.dialog.login.LoginPresenter
import com.github.bkmbigo.fundaschool.presentation.components.dialog.login.LogoutPresenter
import org.koin.dsl.module

val commonModule = module {
    factory<LoginPresenter> { LoginPresenter(get()) }
    factory<LogoutPresenter> { LogoutPresenter(get()) }

    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single<DonationRepository> { DonationRepositoryImpl(get()) }
    single<NewsRepository> { NewsRepositoryImpl(get()) }
    single<ProjectRepository> { ProjectRepositoryImpl(get()) }
    single<SchoolRepository> { SchoolRepositoryImpl(get()) }
    single<UserRepository> { UserRepositoryImpl(get()) }
}