package com.ahmedteleb.madarsofttask.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ahmedteleb.madarsofttask.domain.entity.Gender
import com.ahmedteleb.madarsofttask.domain.entity.UserEntity

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    var id:Int?= null,
    val name: String,
    val age: Int,
    val jobTitle: String,
    val gender: Int)

fun User.toDomainEntity() = UserEntity(id ?: 0 ,name,age,jobTitle,if(gender == 0) Gender.Male else Gender.Female)