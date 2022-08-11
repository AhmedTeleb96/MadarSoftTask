package com.ahmedteleb.madarsofttask.domain.usecases

import com.ahmedteleb.madarsofttask.domain.entity.UserEntity
import com.ahmedteleb.madarsofttask.domain.repository.UserRepository
import javax.inject.Inject

class AddUserUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(userEntity: UserEntity ) =
        userRepository.addUser(userEntity)
}