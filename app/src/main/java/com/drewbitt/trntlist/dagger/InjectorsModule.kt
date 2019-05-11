package com.drewbitt.trntlist.dagger

import com.drewbitt.trntlist.MainActivity
import com.drewbitt.trntlist.dagger.scopes.MainScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class InjectorsModule {

    @ContributesAndroidInjector
    @MainScope
    abstract fun mainActivity(): MainActivity

}
