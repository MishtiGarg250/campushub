package com.campus.hub.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.campus.hub.data.model.Assignment
import com.campus.hub.data.model.Course
import com.campus.hub.data.repository.CampusRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class DashboardUiState(
    val courses: List<Course> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val assignments:List<Assignment> = emptyList(),
)

class DashboardViewModel(
    private val repository: CampusRepository = CampusRepository()
) : ViewModel() {

    private val _uiState =
        MutableStateFlow(DashboardUiState())

    val uiState: StateFlow<DashboardUiState> =
        _uiState.asStateFlow()

    init {
        loadDashboard()
    }

    private fun loadDashboard() {

        viewModelScope.launch {

            _uiState.value =
                DashboardUiState(
                    isLoading = true
                )

            val coursesResult =
                repository.getCourses()

            val assignmentsResult =
                repository.getAssignments()

            _uiState.value =
                DashboardUiState(

                    courses =
                        coursesResult.getOrDefault(
                            emptyList()
                        ),

                    assignments =
                        assignmentsResult.getOrDefault(
                            emptyList()
                        ),

                    isLoading = false,

                    error =
                        if (
                            coursesResult.isFailure ||
                            assignmentsResult.isFailure
                        ) {
                            "Failed to load some data"
                        } else {
                            null
                        }
                )
        }
    }
}