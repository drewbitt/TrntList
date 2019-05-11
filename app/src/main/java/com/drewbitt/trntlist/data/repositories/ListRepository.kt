package com.drewbitt.trntlist.data.repositories

import androidx.lifecycle.MutableLiveData
import com.drewbitt.trntlist.DaggerApp
import com.drewbitt.trntlist.data.model.TrntJson
import com.drewbitt.trntlist.data.room.TrntJsonDao
import com.drewbitt.trntlist.data.service.TrntListApi
import com.drewbitt.trntlist.util.AppExecutors
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

    internal fun getListLiveData(): MutableLiveData<List<TrntJson>> {
        val liveData = MutableLiveData<List<TrntJson>>()

        executors.diskIO.execute {
            try {
                val result = getListRemotely()
                executors.mainThread.execute { liveData.value = result }
            } catch (e: Exception) {
                Timber.e("message[${e.message}]")
                executors.mainThread.execute { liveData.value = null }
            }
        }
        return liveData
    }

/*    private suspend fun getList() = Mutex().withLock {
        return@withLock listCache ?: withContext(Dispatchers.IO) {
            return@withContext getListRemotely()
        }
    }*/

    private fun getListRemotely(): List<TrntJson> =
        try {
            mainList.getList().execute().body()
        } catch (e: IOException) {
            null
        } ?: emptyList()
}