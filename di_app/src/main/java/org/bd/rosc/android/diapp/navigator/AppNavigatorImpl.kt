package org.bd.rosc.android.diapp.navigator

import androidx.fragment.app.FragmentActivity
import org.bd.rosc.android.diapp.R
import org.bd.rosc.android.diapp.ui.ButtonsFragment
import org.bd.rosc.android.diapp.ui.LogsFragment
import javax.inject.Inject

/**
 * Navigator implementation.
 */
class AppNavigatorImpl @Inject constructor(private val activity: FragmentActivity) : AppNavigator {

    override fun navigateTo(screen: Screens) {
        val fragment = when (screen) {
            Screens.BUTTONS -> ButtonsFragment()
            Screens.LOGS -> LogsFragment()
        }

        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment)
            .addToBackStack(fragment::class.java.canonicalName)
            .commit()
    }
}
