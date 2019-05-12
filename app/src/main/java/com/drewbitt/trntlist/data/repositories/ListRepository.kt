package com.drewbitt.trntlist.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.drewbitt.trntlist.Analytics
import com.drewbitt.trntlist.DaggerApp
import com.drewbitt.trntlist.data.model.TrntJson
import com.drewbitt.trntlist.data.room.TrntJsonDao
import com.drewbitt.trntlist.data.service.TrntListApi
import com.drewbitt.trntlist.util.AppExecutors
import com.google.firebase.analytics.FirebaseAnalytics
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class ListRepository {

    init {
        DaggerApp.appComponent.inject(this)
    }

    @Inject lateinit var mainList: TrntListApi
    @Inject lateinit var trntJsonDao: TrntJsonDao
    @Inject lateinit var executors: AppExecutors
    @Inject lateinit var analytics: FirebaseAnalytics

    internal fun getListLiveData(): MutableLiveData<List<TrntJson>> {
        val liveData = MutableLiveData<List<TrntJson>>()

        executors.diskIO.execute {
            try {
                val daoList = trntJsonDao.getAllTrntJson()
                Timber.d("daoList [$daoList]")

                if (daoList.isEmpty()) {
                    val result = getListRemotely()
                    result.forEach {
                        insertTrntJson(it)
                    }
                    executors.mainThread.execute { liveData.value = result }
                } else {
                    executors.mainThread.execute { liveData.value = daoList }
                }
            } catch (e: Exception) {
                Timber.e("message[${e.message}]")
                executors.mainThread.execute { liveData.value = null }
            }
        }
        return liveData
    }

    internal fun getListLiveDataDao(): LiveData<List<TrntJson>> = trntJsonDao.getAllTrntJsonLiveData()

    internal fun insertTrntJson(trntJson: TrntJson): MutableLiveData<List<TrntJson>> {
        val liveData = MutableLiveData<List<TrntJson>>()

        executors.diskIO.execute {
            try {
                trntJsonDao.insertTrntJson(trntJson)
                val result = trntJsonDao.getAllTrntJson()
                executors.mainThread.execute { liveData.value = result }
            } catch (e: Exception) {
                Timber.e("message[${e.message}]")
                executors.mainThread.execute { liveData.value = null }
            }
        }
        return liveData
    }

    internal fun updateTrntJson(trntJson: TrntJson): MutableLiveData<List<TrntJson>> {
        val liveData = MutableLiveData<List<TrntJson>>()
        executors.diskIO.execute {
            try {
                trntJsonDao.updateTrntJson(trntJson)
                val result = trntJsonDao.getAllTrntJson()
                executors.mainThread.execute { liveData.value = result }
            } catch (e: Exception) {
                Timber.e("message[${e.message}]")
                executors.mainThread.execute { liveData.value = null }
            }
        }
        return liveData
    }

    private fun getListRemotely(): List<TrntJson> =
        try {
            mainList.getList().execute().body().also {
                Analytics.NetworkCall("NETWORK_CALL").log(analytics)
            }
        } catch (e: IOException) {
            null
        } ?: emptyList()
}