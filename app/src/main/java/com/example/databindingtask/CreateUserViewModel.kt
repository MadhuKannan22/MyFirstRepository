package com.example.databindingtask

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CreateUserViewModel(
    private val registerRepository: RegisterRepository,
    application: Application
) : AndroidViewModel(application) {

    fun saveUserData(username: String, password: String) {
        viewModelScope.launch {
            val registerEntity = RegisterEntity(0, username, password)
            /*if (isUserExist(registerEntity)) {
                Toast.makeText(
                    getApplication(),
                    "Username and password already exist",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                registerRepository.insertUser(registerEntity)
                Toast.makeText(
                    getApplication(),
                    "Username and password saved successfully",
                    Toast.LENGTH_SHORT
                ).show()
            }*/
            registerRepository.insertUser(registerEntity)


        }


        /*suspend fun isUserExist(registerEntity: RegisterEntity): Boolean {
        return registerRepository.isUserExist(registerEntity.Username)
    }*/
    }
}