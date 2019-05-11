package com.drewbitt.trntlist

import com.drewbitt.trntlist.data.dagger.AppComponent
import com.drewbitt.trntlist.data.dagger.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class DaggerApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        appComponent = DaggerAppComponent
            .builder()
            .context(this)
            .build()
        return appComponent
    }

    companion object {
        lateinit var appComponent: AppComponent
    }

}
