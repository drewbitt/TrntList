package com.drewbitt.trntlist.data

import com.drewbitt.trntlist.data.repositories.ListRepository

class ViewModel(private val listRepository: ListRepository) {

    //list repository
    internal fun getListLiveData() = listRepository.getListLiveData()
}
