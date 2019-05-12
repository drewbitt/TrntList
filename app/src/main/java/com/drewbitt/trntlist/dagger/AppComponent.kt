package com.drewbitt.trntlist.dagger

import android.content.Context
import com.drewbitt.trntlist.DaggerApp
import com.drewbitt.trntlist.dagger.scopes.AppScope
import com.drewbitt.trntlist.data.repositories.ListRepository
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@AppScope
@Component(modules = [(AndroidInjectionModule::class), (AppModule::class), (InjectorsModule::class)])
interface AppComponent : AndroidInjector<DaggerApp> {

    override fun inject(daggerApp: DaggerApp)

    fun inject(listRepository: ListRepository)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent
    }
}
