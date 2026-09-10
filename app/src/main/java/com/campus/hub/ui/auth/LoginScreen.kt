package com.campus.hub.ui.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onRegisterClick: ()-> Unit,
    viewModel: LoginViewModel = viewModel()
) {

    val uiState by
    viewModel.uiState.collectAsState()


    if (uiState.isLoggedIn) {

        onLoginSuccess()

        return
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        verticalArrangement =
            Arrangement.Center
    ) {

        Text(
            text = "Welcome to CampusHub"
        )

        OutlinedTextField(
            value = uiState.email,
            onValueChange =
                viewModel::updateEmail,

            label = {
                Text("Email")
            },

            modifier =
                Modifier.fillMaxWidth()
        )

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
                Modifier.fillMaxWidth()
        )


        if (uiState.error != null) {

            Text(
                text = uiState.error!!
            )
        }


        Button(
            onClick = {
                viewModel.login()
            },

            enabled = !uiState.isLoading,

            modifier =
                Modifier.fillMaxWidth()
        ) {

            if (uiState.isLoading) {

                CircularProgressIndicator()

            } else {

                Text("Login")
            }
        }

        Spacer(
            modifier = Modifier.height(10.dp)
        )
        TextButton(
            onClick = onRegisterClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Don't have an account? Register")
        }
    }
}