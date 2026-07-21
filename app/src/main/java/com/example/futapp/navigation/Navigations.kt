package com.example.futapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.futapp.ui.datailgame.MatchDetailScreen
import com.example.futapp.ui.listgame.MatchListScreen

object Routes {
    const val MATCH_LIST = "matchList"
    const val MATCH_DETAIL = "matchDetail/{matchId}"

    fun matchDetail(matchId: String) = "matchDetail/$matchId"
}

@Composable
fun AppNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Routes.MATCH_LIST,
        modifier = modifier
    ) {
        composable(Routes.MATCH_LIST) {
            MatchListScreen(
                onMatchClick = { matchId ->
                    navController.navigate(Routes.matchDetail(matchId))
                },
                modifier = modifier
            )
        }

        composable(
            route = Routes.MATCH_DETAIL,
            arguments = listOf(
                navArgument("matchId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val matchId = backStackEntry.arguments?.getString("matchId") ?: ""
            MatchDetailScreen(
                matchId = matchId,
                onBackClick = {
                    navController.popBackStack()
                },
                modifier = modifier
            )
        }
    }
}