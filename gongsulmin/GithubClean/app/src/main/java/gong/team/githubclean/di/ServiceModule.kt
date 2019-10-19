package gong.team.githubclean.di

import gong.team.domain.service.ServiceRepository
import gong.team.domain.service.ServiceRepositoryImpl
import org.koin.dsl.module

val serviceModule = module {
    single<ServiceRepository> { ServiceRepositoryImpl(get()) }
}