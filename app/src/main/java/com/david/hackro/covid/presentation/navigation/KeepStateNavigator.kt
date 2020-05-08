package com.david.hackro.covid.presentation.navigation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.fragment.FragmentNavigator

@Navigator.Name("keep_state_fragment")
class KeepStateNavigator(
    private val context: Context,
    private val manager: FragmentManager,
    private val containerId: Int
) : FragmentNavigator(context, manager, containerId) {

    override fun navigate(
        destination: Destination,
        args: Bundle?,
        navOptions: NavOptions?,
        navigatorExtras: Navigator.Extras?
    ): NavDestination? {
        val tag = destination.id.toString()
        val transaction: FragmentTransaction = manager.beginTransaction()

        val currentFragment: Fragment? = manager.primaryNavigationFragment


        var initialNavigate = false
        if (currentFragment != null) {
            transaction.hide(currentFragment)
        } else {
            initialNavigate = true
        }

        var fragment: Fragment? = manager.findFragmentByTag(tag)

        if (fragment == null) {
            fragment =
                manager.fragmentFactory.instantiate(context.classLoader, destination.className)
            transaction.add(containerId, fragment, tag)
        } else {
            transaction.show(fragment)
        }

        transaction.apply {
            setPrimaryNavigationFragment(fragment)
            setReorderingAllowed(true)
        }.commitNow()

        return if (initialNavigate) {
            destination
        } else {
            null
        }
    }
}
