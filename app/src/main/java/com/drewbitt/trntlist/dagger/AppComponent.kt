package com.drewbitt.trntlist.dagger

import android.content.Context
import com.drewbitt.trntlist.DaggerApp
import com.drewbitt.trntlist.data.repositories.HttpRepository
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@AppScope
@Component(modules = [(AndroidSupportInjectionModule::class), (AppModule::class), (InjectorsModule::class)])
interface AppComponent : AndroidInjector<DaggerApp> {

    override fun inject(daggerApp: DaggerApp)

    fun inject(httpRepository: HttpRepository)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent
    }

}
