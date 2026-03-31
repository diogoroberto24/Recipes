package com.project.recipes.navigation

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.project.recipes.screens.CategoryRecipeScreen
import com.project.recipes.screens.HomeScreen
import com.project.recipes.screens.InitialScreen
import com.project.recipes.screens.LoginScreen
import com.project.recipes.screens.SignupScreen

@Composable
fun NavigationRoutes() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Destination.InitialScreen.route
    ){
        composable(Destination.InitialScreen.route){
            InitialScreen(navController)
        }
        composable(
            route =  Destination.HomeScreen.route,
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "https://recipes.project.com/email/{email}"
                    action = Intent.ACTION_VIEW
                }
            ),
            arguments = listOf(navArgument("email"){
                type = NavType.StringType
            })
        ){backStackEntry ->
            var email = backStackEntry.arguments?.getString("email")
            HomeScreen(navController, email)
        }
        composable(Destination.SignupScreen.route){
            SignupScreen(navController)
        }
        composable(Destination.LoginScreen.route){
            LoginScreen(navController)
        }
        composable (
            route = Destination.CategoryRecipeScreen.route,
            arguments = listOf(
                navArgument("categoryId"){
                    type = NavType.IntType
                }
            )
        ){
            val categoryId = it.arguments?.getInt("categoryId")
            CategoryRecipeScreen(categoryId)
        }
    }
}