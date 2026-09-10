package com.campus.hub.ui.auth

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.campus.hub.data.local.TokenManager
import com.campus.hub.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val isLoggedIn: Boolean = false
)

class LoginViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val tokenManager =
        TokenManager(application)

    private val repository =
        AuthRepository(tokenManager)

    private val _uiState =
        MutableStateFlow(LoginUiState())

    val uiState =
        _uiState.asStateFlow()


    fun updateEmail(email: String) {

        _uiState.value =
            _uiState.value.copy(
                email = email
            )
    }


    fun updatePassword(password: String) {

        _uiState.value =
            _uiState.value.copy(
                password = password
            )
    }


    fun login() {

        val state = _uiState.value

        if (
            state.email.isBlank() ||
            state.password.isBlank()
        ) {

            _uiState.value =
                state.copy(
                    error =
                        "Please enter email and password"
                )

            return
        }

        viewModelScope.launch {

            _uiState.value =
                state.copy(
                    isLoading = true,
                    error = null
                )

            repository.login(
                state.email,
                state.password
            )
                .onSuccess {

                    _uiState.value =
                        _uiState.value.copy(
                            isLoading = false,
                            isLoggedIn = true
                        )
                }
                .onFailure { error ->

                    _uiState.value =
                        _uiState.value.copy(
                            isLoading = false,
                            error =
                                error.message
                        )
                }
        }
    }
}