package dev.gitlive.firebase

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InheritableSerialInfo

@OptIn(ExperimentalSerializationApi::class)
@InheritableSerialInfo
@Target(AnnotationTarget.CLASS)
annotation class FirebaseClassDiscriminator(val discriminator: String)