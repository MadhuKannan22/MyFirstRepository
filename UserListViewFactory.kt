package com.example.databindingtask

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class UserListViewFactory(
    private val application: Application,
    private val registerRepository: RegisterRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserListViewModel::class.java)) {
            return UserListViewModel(registerRepository, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
