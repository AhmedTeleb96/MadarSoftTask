package com.ahmedteleb.madarsofttask.app.home.addUser

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmedteleb.madarsofttask.domain.entity.Gender
import com.ahmedteleb.madarsofttask.domain.entity.UserEntity
import com.ahmedteleb.madarsofttask.domain.usecases.AddUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddUserViewModel @Inject constructor(private val addUserUseCase: AddUserUseCase) :
    ViewModel() {


    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?>
        get() = _error

    private val _success = MutableLiveData<Boolean>()
    val success: LiveData<Boolean>
        get() = _success

    fun onSuccessDone() {
        _success.value = false
    }

    fun addUser(name: String, age: String, job: String, gender: Gender?) {
        if (name.isEmpty())
            _error.value = "Please enter name"
        else if (age.isEmpty())
            _error.value = "Please enter valid age"
        else if (job.isEmpty())
            _error.value = "Please enter job title"
        else if (gender == null)
            _error.value = "Please choose gender"
        else {
            _loading.postValue(true)
            viewModelScope.launch{
                val insertSuccess = addUserUseCase.invoke(
                    UserEntity(
                        name = name,
                        age = age.toInt(),
                        jobTitle = job,
                        gender = gender
                    )
                )
                if (!insertSuccess) {
                    _loading.postValue(false)
                    _error.postValue("add user Failed")
                } else {
                    _loading.postValue(false)
                    _success.postValue(true)
                }
            }
            _loading.postValue(false)
        }
    }

}