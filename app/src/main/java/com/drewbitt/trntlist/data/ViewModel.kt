package com.drewbitt.trntlist.data

import com.drewbitt.trntlist.data.model.TrntJson
import com.drewbitt.trntlist.data.repositories.ListRepository

class ViewModel(private val listRepository: ListRepository) {

    // list repository
    internal fun getListLiveData() = listRepository.getListLiveData()

    internal fun insertTrntJson(trntJson: TrntJson) = listRepository.insertTrntJson(trntJson)

    internal fun updateTrntJson(trntJson: TrntJson) = listRepository.updateTrntJson(trntJson)
}
