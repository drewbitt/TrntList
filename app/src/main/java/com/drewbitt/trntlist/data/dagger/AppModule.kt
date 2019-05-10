package com.drewbitt.trntlist.data.dagger

import com.drewbitt.trntlist.data.ViewModel
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    @AppScope
    fun provideViewModel() = ViewModel()

}
