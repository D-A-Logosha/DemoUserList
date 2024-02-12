package com.example.demouserlist.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demouserlist.data.User
import com.example.demouserlist.data.UsersListener
import com.example.demouserlist.data.UsersRepository

class UserListViewModel : ViewModel() {

    private val usersRepository = UsersRepository()

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users

    private val usersListener: UsersListener = {
        _users.value = it
    }

    init {
        usersRepository.addListener(usersListener)
    }

    override fun onCleared() {
        super.onCleared()
        usersRepository.removeListener(usersListener)
    }

    fun editUser(user: User) {
        usersRepository.editUser(user)
    }
}