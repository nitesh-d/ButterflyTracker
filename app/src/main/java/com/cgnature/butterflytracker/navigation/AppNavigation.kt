package com.cgnature.butterflytracker.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.cgnature.butterflytracker.ui.screens.AddSightingScreen
import com.cgnature.butterflytracker.ui.screens.BirdChecklistScreen
import com.cgnature.butterflytracker.ui.screens.ButterflyChecklistScreen
import com.cgnature.butterflytracker.ui.screens.ChecklistsScreen
import com.cgnature.butterflytracker.ui.screens.ComingSoonScreen
import com.cgnature.butterflytracker.ui.screens.DashboardScreen
import com.cgnature.butterflytracker.ui.screens.HomeScreen
import com.cgnature.butterflytracker.ui.screens.PlantChecklistScreen
import com.cgnature.butterflytracker.ui.screens.ProfileScreen
import com.cgnature.butterflytracker.ui.screens.RouteScreen
import com.cgnature.butterflytracker.ui.screens.TripDetailScreen
import com.cgnature.butterflytracker.viewmodel.SightingViewModel
import java.net.URLDecoder
import java.net.URLEncoder

object Routes {
    const val DASHBOARD = "dashboard"
    const val HOME = "home" // "Records" — the flat chronological sightings list
    const val ADD_SIGHTING = "add_sighting"
    const val ROUTE = "route" // live map for the currently active trip
    const val BUTTERFLY_CHECKLIST = "butterfly_checklist"
    const val CHECKLISTS = "checklists" // list of all trips
    const val TRIP_DETAIL = "trip_detail/{tripId}"
    const val COMING_SOON = "coming_soon/{category}"
    const val PROFILE = "profile"
    const val PLANT_CHECKLIST = "plant_checklist"
    const val BIRD_CHECKLIST = "bird_checklist"
    fun tripDetail(tripId: Long) = "trip_detail/$tripId"
    fun comingSoon(category: String) = "coming_soon/${URLEncoder.encode(category, "UTF-8")}"
}

@Composable
fun AppNavigation(viewModel: SightingViewModel) {
    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.DASHBOARD) {

        composable(Routes.DASHBOARD) {
            DashboardScreen(
                viewModel = viewModel,
                onOpenButterflies = { navController.navigate(Routes.BUTTERFLY_CHECKLIST) },
                onOpenBirds = { navController.navigate(Routes.BIRD_CHECKLIST) },
                onOpenPlants = { navController.navigate(Routes.PLANT_CHECKLIST) },
                onSeeAllChecklists = { navController.navigate(Routes.CHECKLISTS) },
                onOpenTrip = { tripId -> navController.navigate(Routes.tripDetail(tripId)) },
                onAddClick = { navController.navigate(Routes.ADD_SIGHTING) },
                onMenuClick = { navController.navigate(Routes.PROFILE) },
                onNavHome = { /* already on dashboard/home */ },
                onNavChecklists = { navController.navigate(Routes.CHECKLISTS) },
                onNavRecords = { navController.navigate(Routes.HOME) },
                onNavProfile = { navController.navigate(Routes.PROFILE) }
            )
        }

        composable(Routes.HOME) {
            HomeScreen(
                viewModel = viewModel,
                onAddSighting = { navController.navigate(Routes.ADD_SIGHTING) },
                onViewRoute = { navController.navigate(Routes.ROUTE) }
            )
        }

        composable(Routes.ADD_SIGHTING) {
            AddSightingScreen(
                viewModel = viewModel,
                onDone = { navController.popBackStack() }
            )
        }

        composable(Routes.ROUTE) {
            RouteScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.BUTTERFLY_CHECKLIST) {
            ButterflyChecklistScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.PLANT_CHECKLIST) {
            PlantChecklistScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }
        composable(Routes.BIRD_CHECKLIST) {
            BirdChecklistScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.CHECKLISTS) {
            ChecklistsScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onOpenTrip = { tripId -> navController.navigate(Routes.tripDetail(tripId)) }
            )
        }

        composable(
            route = Routes.TRIP_DETAIL,
            arguments = listOf(navArgument("tripId") { type = NavType.LongType })
        ) { backStackEntry ->
            val tripId = backStackEntry.arguments?.getLong("tripId") ?: return@composable
            TripDetailScreen(
                viewModel = viewModel,
                tripId = tripId,
                onBack = { navController.popBackStack() }
            )
        }

        composable(
            route = Routes.COMING_SOON,
            arguments = listOf(navArgument("category") { type = NavType.StringType })
        ) { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category")
                ?.let { URLDecoder.decode(it, "UTF-8") } ?: "This category"
            ComingSoonScreen(
                categoryName = category,
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.PROFILE) {
            ProfileScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}