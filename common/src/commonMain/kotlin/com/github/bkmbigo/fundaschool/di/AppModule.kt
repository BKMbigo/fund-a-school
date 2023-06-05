package com.github.bkmbigo.fundaschool.di

import com.github.bkmbigo.fundaschool.data.firebase.repositories.AuthRepositoryImpl
import com.github.bkmbigo.fundaschool.data.firebase.repositories.DonationRepositoryImpl
import com.github.bkmbigo.fundaschool.data.firebase.repositories.NewsRepositoryImpl
import com.github.bkmbigo.fundaschool.data.firebase.repositories.ProjectRepositoryImpl
import com.github.bkmbigo.fundaschool.data.firebase.repositories.SchoolRepositoryImpl
import com.github.bkmbigo.fundaschool.data.firebase.repositories.SponsorshipRepositoryImpl
import com.github.bkmbigo.fundaschool.data.firebase.repositories.UserRepositoryImpl
import com.github.bkmbigo.fundaschool.domain.repositories.firebase.AuthRepository
import com.github.bkmbigo.fundaschool.domain.repositories.firebase.DonationRepository
import com.github.bkmbigo.fundaschool.domain.repositories.firebase.NewsRepository
import com.github.bkmbigo.fundaschool.domain.repositories.firebase.ProjectRepository
import com.github.bkmbigo.fundaschool.domain.repositories.firebase.SchoolRepository
import com.github.bkmbigo.fundaschool.domain.repositories.firebase.SponsorshipRepository
import com.github.bkmbigo.fundaschool.domain.repositories.firebase.UserRepository
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
    single<SponsorshipRepository> { SponsorshipRepositoryImpl(get()) }
    single<UserRepository> { UserRepositoryImpl(get()) }
}