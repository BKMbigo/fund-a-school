package com.github.bkmbigo.fundaschool.di

import com.github.bkmbigo.fundaschool.data.repositories.*
import com.github.bkmbigo.fundaschool.domain.repositories.*
import com.github.bkmbigo.fundaschool.presentation.components.dialog.login.LoginPresenter
import com.github.bkmbigo.fundaschool.presentation.components.dialog.login.LogoutPresenter
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.firestore.firestore
import org.koin.core.module.Module
import org.koin.dsl.module

val commonModule = module {
    factory<LoginPresenter> { LoginPresenter(get()) }
    factory<LogoutPresenter> { LogoutPresenter(get()) }


    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    single<DonationRepository> { DonationRepositoryImpl(get()) }
    single<NewsRepository> { NewsRepositoryImpl(get()) }
    single<ProjectRepository> { ProjectRepositoryImpl(get()) }
    single<SchoolRepository> { SchoolRepositoryImpl(get()) }
    single<UserRepository> { UserRepositoryImpl(get()) }

    single<FirebaseAuth> { Firebase.auth }
    single<FirebaseFirestore> { Firebase.firestore }
}