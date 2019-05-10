package com.drewbitt.trntlist.dagger

import android.content.Context
import com.drewbitt.trntlist.DaggerApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@AppScope
@Component(modules = [(AndroidSupportInjectionModule::class), (AppModule::class), (InjectorsModule::class)])
interface AppComponent : AndroidInjector<DaggerApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent
    }

}
