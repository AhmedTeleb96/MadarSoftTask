package com.ahmedteleb.madarsofttask.data.repository

import com.ahmedteleb.madarsofttask.data.db.UserDao
import com.ahmedteleb.madarsofttask.data.models.User
import com.ahmedteleb.madarsofttask.data.models.toDomainEntity
import com.ahmedteleb.madarsofttask.domain.entity.UserEntity
import com.ahmedteleb.madarsofttask.domain.repository.UserRepository
import com.ahmedteleb.madarsofttask.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImp @Inject constructor(private val userDao: UserDao) : UserRepository {
    override suspend fun addUser(userEntity: UserEntity): Boolean {
        val userModel = User(
            name = userEntity.name,
            age = userEntity.age,
            jobTitle = userEntity.jobTitle,
            gender = userEntity.gender.ordinal
        )
        val isSuccess = userDao.insert(userModel)
        return isSuccess > 0
    }

    override fun getAllUsers(): Flow<Resource<List<UserEntity>>> =
        flow {
            try {
                val users = userDao.getAllUsers()

                emitAll(
                    users.map {
                        Resource.Success(it.map { user -> user.toDomainEntity() })
                    }
                )
            } catch (ex: Exception) {
                Resource.Failed<String>(ex.message ?: "Error")
            }

        }

}