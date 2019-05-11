package com.drewbitt.trntlist.dagger

import com.drewbitt.trntlist.MainActivity
import com.drewbitt.trntlist.dagger.scopes.DetailsScope
import com.drewbitt.trntlist.dagger.scopes.MainScope
import com.drewbitt.trntlist.ui.DetailsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class InjectorsModule {

    @ContributesAndroidInjector
    @MainScope
    abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector
    @DetailsScope
    abstract fun detailsActivity(): DetailsActivity

}
