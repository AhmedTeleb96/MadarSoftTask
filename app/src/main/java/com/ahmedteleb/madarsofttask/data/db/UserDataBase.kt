package com.ahmedteleb.madarsofttask.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ahmedteleb.madarsofttask.data.models.User

@Database(
    entities = [User::class],
    version = 1
)
abstract class UserDataBase : RoomDatabase() {

    abstract fun getUserDao(): UserDao

    companion object {
        @Volatile // it is mean that other thread can see when the thread changes this instance
        private var instance: UserDataBase? = null //that is an instance of our database, so we only have a single instance of DB

        private val LOCK = Any() //that make sure that the db has only one instance
        operator fun invoke(context: Context) = instance
            ?: synchronized(LOCK) { //this method mean that not anther threat sets this instance to some thing while we already set it
                instance ?: createDataBase(context).also { instance = it }
            }

        private fun createDataBase(context: Context) = Room.databaseBuilder( // this method to access the actual db functions
            context.applicationContext,
            UserDataBase::class.java,
            "user_db"
        ).build()

    }
}