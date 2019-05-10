package com.drewbitt.trntlist.ui.recyclerview

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.drewbitt.trntlist.MainFragment
import com.drewbitt.trntlist.data.ViewModel
import com.drewbitt.trntlist.data.model.TrntJson

class RecyclerAdapter(private val mainFragment: MainFragment, val viewModel: ViewModel) : BaseQuickAdapter<TrntJson, BaseViewHolder>() {

}
