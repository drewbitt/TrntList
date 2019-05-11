package com.drewbitt.trntlist.dagger

import com.drewbitt.trntlist.BuildConfig
import com.drewbitt.trntlist.data.ViewModel
import com.drewbitt.trntlist.data.service.TrntListApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
class AppModule {

    @Provides
    @AppScope
    fun provideViewModel() = ViewModel()

    @Provides
    @AppScope
    @Named("main")
    fun provideMainHttpClient(): Retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.trntlistApi)
            .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @AppScope
    @Named("create")
    fun provideAddHttpClient(): Retrofit = Retrofit.Builder()
    .baseUrl(BuildConfig.createtrntApi)
    .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @AppScope
    @Named("mainList")
    fun provideMainList(retrofit: Retrofit): TrntListApi = retrofit.create(TrntListApi::class.java)

}
