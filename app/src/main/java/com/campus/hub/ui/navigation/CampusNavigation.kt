package com.campus.hub.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.campus.hub.ui.auth.LoginScreen
import com.campus.hub.ui.auth.RegisterScreen
import com.campus.hub.ui.dashboard.DashboardScreen

@Composable
fun CampusNavigation() {

    val navController =
        rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        // LOGIN

        composable("login") {

            LoginScreen(

                onLoginSuccess = {

                    navController.navigate(
                        "dashboard"
                    ) {

                        popUpTo("login") {
                            inclusive = true
                        }
                    }
                },

                onRegisterClick = {

                    navController.navigate(
                        "register"
                    )
                }
            )
        }


        // REGISTER

        composable("register") {

            RegisterScreen(

                onRegistrationSuccess = {

                    navController.navigate(
                        "login"
                    ) {

                        popUpTo("register") {
                            inclusive = true
                        }
                    }
                },

                onLoginClick = {

                    navController.popBackStack()
                }
            )
        }


        // DASHBOARD

        composable("dashboard") {

            DashboardScreen()
        }
    }
}