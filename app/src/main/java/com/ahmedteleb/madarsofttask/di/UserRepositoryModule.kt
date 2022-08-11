package com.ahmedteleb.madarsofttask.di

import com.ahmedteleb.madarsofttask.data.db.UserDao
import com.ahmedteleb.madarsofttask.data.repository.UserRepositoryImp
import com.ahmedteleb.madarsofttask.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class UserRepositoryModule {

    @Singleton
    @Provides
    fun provideHomeRepository(userDao: UserDao): UserRepository = UserRepositoryImp(userDao)
}