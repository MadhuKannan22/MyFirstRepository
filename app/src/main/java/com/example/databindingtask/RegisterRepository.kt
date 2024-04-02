package com.example.databindingtask

import kotlinx.coroutines.flow.Flow

class RegisterRepository(private val registerDatabaseDao: RegisterDatabaseDao) {

    suspend fun insertUser(registerEntity: RegisterEntity) {
        return registerDatabaseDao.insertUser(registerEntity)

    }

    suspend fun updateUser(registerEntity: RegisterEntity) {
        return registerDatabaseDao.updateUser(registerEntity)
    }

    suspend fun getUserByUsername(username: String): RegisterEntity? {

        return registerDatabaseDao.getUserByUsername(username)
    }

    fun getAllUsers(): Flow<List<RegisterEntity>> {
        return registerDatabaseDao.getAllUsers()

    }

    suspend fun deleteUser(registerEntity: RegisterEntity) {
        return registerDatabaseDao.deleteUser(registerEntity)
    }

    suspend fun isUserExist(Username: String): Boolean {
        return registerDatabaseDao.getUserByUsername(Username) != null

    }

    /*suspend fun deleteItemUsingId(itemId: String): Int {
        return registerDatabaseDao.deleteItemUsingId(itemId)
    }*/

    suspend fun verifyUser(Username: String, Password: String): Boolean {
        val user = registerDatabaseDao.getUserByUsername(Username)
        return user?.Password == Password

    }


}