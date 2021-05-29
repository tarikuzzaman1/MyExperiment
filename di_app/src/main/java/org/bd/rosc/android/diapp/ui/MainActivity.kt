package org.bd.rosc.android.diapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import org.bd.rosc.android.diapp.R
import org.bd.rosc.android.diapp.navigator.AppNavigator
import org.bd.rosc.android.diapp.navigator.Screens
import javax.inject.Inject

/**
 * Main activity of the application.
 *
 * Container for the Buttons & Logs fragments. This activity simply tracks clicks on buttons.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject lateinit var navigator: AppNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            navigator.navigateTo(Screens.BUTTONS)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

        if (supportFragmentManager.backStackEntryCount == 0) {
            finish()
        }
    }
}
