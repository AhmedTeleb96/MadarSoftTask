package com.ahmedteleb.madarsofttask.di

import android.app.Application
import com.ahmedteleb.madarsofttask.data.db.UserDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule{

    @Singleton
    @Provides
    fun provideDatabase(application: Application) = UserDataBase.invoke(application)

    @Singleton
    @Provides
    fun provideUserDao(userDataBase: UserDataBase) = userDataBase.getUserDao()
}
