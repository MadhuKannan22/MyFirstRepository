package com.example.databindingtask

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class UserListViewModel(
    private val registerRepository: RegisterRepository,
    application: Application
) : AndroidViewModel(application) {
    val userList: Flow<List<RegisterEntity>> = registerRepository.getAllUsers()

    fun deleteUser(registerEntity: RegisterEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            registerRepository.deleteUser(registerEntity)
        }
    }

    /*fun deleteItembyId(itemId : String) {
        viewModelScope.launch(Dispatchers.IO) {
            registerRepository.deleteItemUsingId(itemId)
        }
    }*/
}
