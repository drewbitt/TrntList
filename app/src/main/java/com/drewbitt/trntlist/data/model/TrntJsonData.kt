package com.drewbitt.trntlist.data.model

abstract class TrntJsonData {
    abstract var autoId: Int
    abstract var name: String
    abstract var created: String
    abstract var private: Boolean
    abstract var announce: List<String>
    abstract var infoHash: String
    // abstract var files: Array<FilesClass>
    /* other options include
    pieces/piece length, length, createdBy, infoHash buffer, urlList
     */
}