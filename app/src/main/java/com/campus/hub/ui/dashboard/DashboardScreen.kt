package com.campus.hub.ui.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.campus.hub.data.model.Assignment
import com.campus.hub.data.model.Course


@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = viewModel()
) {

    val uiState by viewModel.uiState.collectAsState()


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),

        verticalArrangement =
            Arrangement.spacedBy(16.dp)
    ) {

        item {

            Text(
                text = "CampusHub",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Text(
                text = "Today's Classes",
                style = MaterialTheme.typography.titleMedium
            )
        }


        // Loading
        if (uiState.isLoading) {

            item {

                CircularProgressIndicator()
            }
        }


        // Error
        if (uiState.error != null) {

            item {

                Text(
                    text = "Error: ${uiState.error}",
                    color =
                        MaterialTheme.colorScheme.error
                )
            }
        }


        // Courses
        items(uiState.courses) { course ->

            CourseCard(course)
        }

        item{
            Text(
                text = "Upcoming Assignments",
                style =MaterialTheme.typography.titleLarge
            )
        }
        items(uiState.assignments){
            assignment -> AssignmentCard(assignment)
        }
    }
}


@Composable
private fun CourseCard(
    course: Course
) {

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = course.name,
                style =
                    MaterialTheme.typography.titleMedium
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Text(
                text = course.code,
                style =
                    MaterialTheme.typography.bodySmall
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text =
                    "${course.startTime} - ${course.endTime}"
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Text(
                text = course.room
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Text(
                text = course.teacher,
                style =
                    MaterialTheme.typography.bodySmall
            )
        }
    }
}


@Composable
private fun AssignmentCard(
    assignment: Assignment
){
    Card(
        modifier = Modifier.fillMaxWidth()
    ){
        Column(
            modifier = Modifier.padding(16.dp)
        ){
            Text(
                text = assignment.title,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(
                modifier = Modifier.height(4.dp)
            )
        }
        Text(
            text = "Due: ${assignment.duedate}",
            style = MaterialTheme.typography.labelMedium
        )
    }
}