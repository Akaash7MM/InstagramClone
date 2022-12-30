package com.example.instagramclone.di

import com.example.data.repository.FakeAuthRepository
import com.example.domain.usecases.GetCreateUserUseCase
import com.example.domain.usecases.GetFetchDetailsUseCase
import com.example.domain.usecases.GetLoginUserUseCase
import com.example.domain.usecases.GetSaveDetailsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

//@Module
//@TestInstallIn(
//    components = [SingletonComponent::class],
//    replaces = [UseCaseModule::class]
//)
//class FakeModules {
//
//    @Provides
//    fun providesLoginUserUseCase(authRepository: FakeAuthRepository): GetLoginUserUseCase {
//        return GetLoginUserUseCase(authRepository)
//    }
//
//    @Provides
//    fun providesCreateUserUseCase(authRepository: FakeAuthRepository): GetCreateUserUseCase {
//        return GetCreateUserUseCase(authRepository)
//    }
//
//    @Provides
//    fun getSaveDetailsUseCase(authRepository: FakeAuthRepository): GetSaveDetailsUseCase {
//        return GetSaveDetailsUseCase(authRepository)
//    }
//
//    @Provides
//    fun getFetchDetailsUseCase(authRepository: FakeAuthRepository): GetFetchDetailsUseCase {
//        return GetFetchDetailsUseCase(authRepository)
//    }
// }
