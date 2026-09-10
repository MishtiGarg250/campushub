package com.campus.hub.ui.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun RegisterScreen(
    onRegistrationSuccess: () -> Unit,
    onLoginClick: () -> Unit,
    viewModel: RegisterViewModel = viewModel()
) {

    val uiState by viewModel.uiState.collectAsState()


    if (uiState.isRegistered) {

        onRegistrationSuccess()

        return
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(
                rememberScrollState()
            )
            .padding(24.dp),

        verticalArrangement =
            Arrangement.Center
    ) {

        Text(
            text = "Create your CampusHub account"
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )


        // Name

        OutlinedTextField(
            value = uiState.name,

            onValueChange =
                viewModel::updateName,

            label = {
                Text("Full Name")
            },

            modifier =
                Modifier.fillMaxWidth(),

            singleLine = true
        )


        Spacer(
            modifier = Modifier.height(10.dp)
        )


        // Email

        OutlinedTextField(
            value = uiState.email,

            onValueChange =
                viewModel::updateEmail,

            label = {
                Text("Email")
            },

            modifier =
                Modifier.fillMaxWidth(),

            singleLine = true
        )


        Spacer(
            modifier = Modifier.height(10.dp)
        )


        // Password

        OutlinedTextField(
            value = uiState.password,

            onValueChange =
                viewModel::updatePassword,

            label = {
                Text("Password")
            },

            visualTransformation =
                PasswordVisualTransformation(),

            modifier =
                Modifier.fillMaxWidth(),

            singleLine = true
        )


        Spacer(
            modifier = Modifier.height(10.dp)
        )


        // Roll Number

        OutlinedTextField(
            value = uiState.rollNo,

            onValueChange =
                viewModel::updateRollNo,

            label = {
                Text("Roll Number")
            },

            modifier =
                Modifier.fillMaxWidth(),

            singleLine = true
        )


        Spacer(
            modifier = Modifier.height(10.dp)
        )


        // Branch

        OutlinedTextField(
            value = uiState.branch,

            onValueChange =
                viewModel::updateBranch,

            label = {
                Text("Branch")
            },

            modifier =
                Modifier.fillMaxWidth(),

            singleLine = true
        )


        Spacer(
            modifier = Modifier.height(10.dp)
        )


        // Semester

        OutlinedTextField(
            value = uiState.semester,

            onValueChange =
                viewModel::updateSemester,

            label = {
                Text("Semester")
            },

            modifier =
                Modifier.fillMaxWidth(),

            singleLine = true
        )


        Spacer(
            modifier = Modifier.height(16.dp)
        )


        // Error

        if (uiState.error != null) {

            Text(
                text = uiState.error!!
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )
        }


        // Register button

        Button(
            onClick = {
                viewModel.register()
            },

            enabled = !uiState.isLoading,

            modifier =
                Modifier.fillMaxWidth()
        ) {

            if (uiState.isLoading) {

                CircularProgressIndicator()

            } else {

                Text("Create Account")
            }
        }


        Spacer(
            modifier = Modifier.height(10.dp)
        )


        TextButton(
            onClick = onLoginClick,

            modifier =
                Modifier.fillMaxWidth()
        ) {

            Text(
                text =
                    "Already have an account? Login"
            )
        }
    }
}