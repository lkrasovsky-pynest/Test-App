package com.transistorapps.userstestapp.extensions

import androidx.navigation.NavController
import androidx.navigation.NavDirections

fun NavController.navigateSafe(directions: NavDirections) {
    val action =
        currentDestination?.getAction(directions.actionId) ?: graph.getAction(directions.actionId)
    if (action != null && currentDestination?.id != action.destinationId) {
        navigate(directions)
    }
}