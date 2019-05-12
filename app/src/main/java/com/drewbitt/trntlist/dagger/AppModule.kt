package com.drewbitt.trntlist.dagger

import android.content.Context
import androidx.room.Room
import com.drewbitt.trntlist.BuildConfig
import com.drewbitt.trntlist.MainActivity
import com.drewbitt.trntlist.dagger.scopes.AppScope
import com.drewbitt.trntlist.data.ViewModel
import com.drewbitt.trntlist.data.repositories.ListRepository
import com.drewbitt.trntlist.data.room.TrntListDatabase
import com.drewbitt.trntlist.data.service.TrntListApi
import com.drewbitt.trntlist.util.AppExecutors
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
class AppModule {

    @Provides
    @AppScope
    fun provideViewModel(listRepository: ListRepository) = ViewModel(listRepository)

    @Provides
    @AppScope
    fun provideAppDatabaseDao(context: Context) = Room
        .databaseBuilder(context, TrntListDatabase::class.java, "TrrntListDatabase2")
        .build()
        .trntJsonDao()

    @Provides
    @AppScope
    @Named("main")
    fun provideMainHttpClient(): Retrofit {
        val gsonBuilder = GsonBuilder().serializeNulls()
        return Retrofit.Builder()
            .baseUrl(BuildConfig.trntlistApi)
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
            .build()
    }

    @Provides
    @AppScope
    @Named("create")
    fun provideAddHttpClient(): Retrofit = Retrofit.Builder()
    .baseUrl(BuildConfig.createtrntApi)
    .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @AppScope
    fun provideMainList(@Named("main") retrofit: Retrofit): TrntListApi = retrofit.create(TrntListApi::class.java)

    @Provides
    @AppScope
    fun provideListRepository() = ListRepository()

    @Provides
    @AppScope
    fun provideMainActivity() = MainActivity()

    @Provides
    @AppScope
    fun provideAppExecutors() = AppExecutors()

    @Provides
    @AppScope
    fun provideAnalytics(context: Context) = FirebaseAnalytics.getInstance(context)
}
