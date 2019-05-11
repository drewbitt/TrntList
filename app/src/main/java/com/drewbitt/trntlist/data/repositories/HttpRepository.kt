package com.drewbitt.trntlist.data.repositories

import com.drewbitt.trntlist.DaggerApp
import com.drewbitt.trntlist.data.service.TrntListApi
import javax.inject.Inject
import javax.inject.Named

class HttpRepository() {

    init {
        DaggerApp.appComponent.inject(this)
    }

    @Inject
    @Named("mainList")
    lateinit var mainList: TrntListApi



}
