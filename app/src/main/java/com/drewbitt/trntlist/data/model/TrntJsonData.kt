package com.drewbitt.trntlist.data.model

abstract class TrntJsonData {
    abstract var autoId: Int
    abstract var name: String
    abstract var created: String
    abstract var private: Boolean
    abstract var announce: MutableList<String>
    abstract var infoHash: String
    abstract var files: Map<String, String>
    /* other options include
    pieces/piece length, length, createdBy, infoHash buffer, urlList
     */
}