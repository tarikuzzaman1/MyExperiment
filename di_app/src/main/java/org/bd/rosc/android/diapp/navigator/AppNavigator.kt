package org.bd.rosc.android.diapp.navigator

/**
 * Available screens.
 */
enum class Screens {
    BUTTONS,
    LOGS
}

/**
 * Interfaces that defines an app navigator.
 */
interface AppNavigator {
    // Navigate to a given screen.
    fun navigateTo(screen: Screens)
}
