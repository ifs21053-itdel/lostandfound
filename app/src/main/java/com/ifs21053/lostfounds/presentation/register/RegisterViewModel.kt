package com.ifs21053.lostfounds.presentation.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ifs21053.lostfounds.data.remote.MyResult
import com.ifs21053.lostfounds.data.remote.response.DelcomResponse
import com.ifs21053.lostfounds.data.repository.AuthRepository
import com.ifs21053.lostfounds.presentation.ViewModelFactory

class RegisterViewModel(
    private val authRepository: AuthRepository,
) : ViewModel() {

    fun register(name: String, email: String, password: String):
            LiveData<MyResult<DelcomResponse>> {
        return authRepository.register(name, email, password).asLiveData()
    }

    companion object {
        @Volatile
        private var INSTANCE: RegisterViewModel? = null
        fun getInstance(
            authRepository: AuthRepository
        ): RegisterViewModel {
            synchronized(ViewModelFactory::class.java) {
                INSTANCE = RegisterViewModel(
                    authRepository
                )
            }
            return INSTANCE as RegisterViewModel
        }
    }
}