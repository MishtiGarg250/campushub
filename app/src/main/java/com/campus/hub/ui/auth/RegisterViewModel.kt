package com.campus.hub.ui.auth

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.campus.hub.data.model.RegisterRequest
import com.campus.hub.data.repository.AuthRepository
import com.campus.hub.data.local.TokenManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class RegisterUiState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val rollNo: String = "",
    val branch: String = "",
    val semester: String = "",

    val isLoading: Boolean = false,
    val error: String? = null,
    val isRegistered: Boolean = false
)

class RegisterViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val tokenManager =
        TokenManager(application)

    private val repository =
        AuthRepository(tokenManager)

    private val _uiState =
        MutableStateFlow(RegisterUiState())

    val uiState =
        _uiState.asStateFlow()


    fun updateName(value: String) {
        _uiState.value =
            _uiState.value.copy(
                name = value
            )
    }


    fun updateEmail(value: String) {
        _uiState.value =
            _uiState.value.copy(
                email = value
            )
    }


    fun updatePassword(value: String) {
        _uiState.value =
            _uiState.value.copy(
                password = value
            )
    }


    fun updateRollNo(value: String) {
        _uiState.value =
            _uiState.value.copy(
                rollNo = value
            )
    }


    fun updateBranch(value: String) {
        _uiState.value =
            _uiState.value.copy(
                branch = value
            )
    }


    fun updateSemester(value: String) {
        _uiState.value =
            _uiState.value.copy(
                semester = value
            )
    }


    fun register() {

        val state = _uiState.value

        if (state.name.isBlank()) {
            setError("Please enter your name")
            return
        }

        if (state.email.isBlank()) {
            setError("Please enter your email")
            return
        }

        if (state.password.length < 6) {
            setError("Password must be at least 6 characters")
            return
        }

        if (state.rollNo.isBlank()) {
            setError("Please enter your roll number")
            return
        }

        if (state.branch.isBlank()) {
            setError("Please enter your branch")
            return
        }

        val semester =
            state.semester.toIntOrNull()

        if (semester == null) {
            setError("Please enter a valid semester")
            return
        }

        viewModelScope.launch {

            _uiState.value =
                state.copy(
                    isLoading = true,
                    error = null
                )

            val request =
                RegisterRequest(
                    name = state.name,
                    email = state.email,
                    password = state.password,
                    rollNo = state.rollNo,
                    branch = state.branch,
                    semester = semester
                )

            repository.register(request)

                .onSuccess {

                    _uiState.value =
                        _uiState.value.copy(
                            isLoading = false,
                            isRegistered = true
                        )
                }

                .onFailure { error ->

                    _uiState.value =
                        _uiState.value.copy(
                            isLoading = false,
                            error =
                                error.message
                                    ?: "Registration failed"
                        )
                }
        }
    }


    private fun setError(message: String) {

        _uiState.value =
            _uiState.value.copy(
                error = message
            )
    }
}