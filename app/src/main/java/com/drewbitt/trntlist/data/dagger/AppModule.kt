package com.drewbitt.trntlist.data.dagger

import com.drewbitt.trntlist.BuildConfig
import com.drewbitt.trntlist.DaggerApp
import com.drewbitt.trntlist.R
import com.drewbitt.trntlist.data.ViewModel
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class AppModule {

    @Provides
    @AppScope
    fun provideViewModel() = ViewModel()

    @Provides
    @AppScope
    fun provideMainHttpClient(): Retrofit.Builder = Retrofit.Builder()
            .baseUrl(BuildConfig.trntlistApi)
            .addConverterFactory(GsonConverterFactory.create())

    @Provides
    @AppScope
    fun provideAddHttpClient(): Retrofit.Builder = Retrofit.Builder()
    .baseUrl(BuildConfig.createtrntApi)
    .addConverterFactory(GsonConverterFactory.create())

}
