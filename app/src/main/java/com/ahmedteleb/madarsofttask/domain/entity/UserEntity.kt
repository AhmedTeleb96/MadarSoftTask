package com.ahmedteleb.madarsofttask.domain.entity

data class UserEntity(
    val id: Int? = null,
    val name: String,
    val age: Int,
    val jobTitle: String,
    val gender: Gender,
)

enum class Gender {
    Male, Female
}
