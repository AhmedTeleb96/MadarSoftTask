package com.ahmedteleb.madarsofttask.domain.repository

import com.ahmedteleb.madarsofttask.domain.entity.UserEntity
import com.ahmedteleb.madarsofttask.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun addUser(userEntity: UserEntity) : Boolean
    fun getAllUsers() : Flow<Resource<List<UserEntity>>>
}