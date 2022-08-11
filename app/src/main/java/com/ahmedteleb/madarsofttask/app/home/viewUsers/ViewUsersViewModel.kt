package com.ahmedteleb.madarsofttask.app.home.viewUsers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmedteleb.madarsofttask.domain.entity.UserEntity
import com.ahmedteleb.madarsofttask.domain.usecases.GetAllUsersUseCase
import com.ahmedteleb.madarsofttask.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewUsersViewModel @Inject constructor(private val getAllUsersUseCase: GetAllUsersUseCase): ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?>
        get() = _error

    private val _users = MutableLiveData<List<UserEntity>?>()
    val users: LiveData<List<UserEntity>?>
        get() = _users

    private fun getAllUsers() {
        _loading.postValue(true)
        viewModelScope.launch{
           getAllUsersUseCase.invoke().collect { resource ->
               when (resource) {
                   is Resource.Success -> _users.postValue(resource.data)
                   is Resource.Failed -> _error.postValue(resource.message)
               }

           }
        }
        _loading.postValue(false)
    }

    init {
       getAllUsers()
    }
}