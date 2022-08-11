package com.ahmedteleb.madarsofttask.domain.usecases

import com.ahmedteleb.madarsofttask.domain.repository.UserRepository
import javax.inject.Inject

class GetAllUsersUseCase @Inject constructor(private val userRepository: UserRepository) {
     operator fun invoke( ) = userRepository.getAllUsers()
}