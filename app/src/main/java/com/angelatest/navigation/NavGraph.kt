package com.angelatest.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.angelatest.MainViewModel
import com.angelatest.ui.home.HomeScreen
import com.angelatest.ui.home.detailScreen.DetailScreen
import com.angelatest.ui.login.LoginScreen

@Composable
fun AppNavGraph(viewModel: MainViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(onLogin = { username ->
                viewModel.setUserName(username)
                navController.navigate("home")
            })
        }
        composable("home") {
            HomeScreen(viewModel, onCardClick = { navController.navigate("details/${it}") })
        }
        composable("details/{drug}") { backStackEntry ->
            val medicineName = backStackEntry.arguments?.getString("drug")
            val problemsList by viewModel.problemsList.collectAsState(initial = emptyList())
            val drug = problemsList.find { it["name"] == medicineName }
            drug?.let { DetailScreen(drug = it) }

        }
    }
}