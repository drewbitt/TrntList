package com.drewbitt.trntlist

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.FirebaseAnalytics.Param.ITEM_NAME

sealed class Analytics(private val name: String, private val bundle: Bundle) {

    fun log(firebaseAnalytics: FirebaseAnalytics) = firebaseAnalytics.logEvent(name, bundle)

    internal class NetworkCall(logName: String) : Analytics(NETWORK_CALL, Bundle().also {
        it.putString(ITEM_NAME, logName)
    }) {
        companion object {
            const val NETWORK_CALL = "NETWORK_CALL"
        }
    }
}