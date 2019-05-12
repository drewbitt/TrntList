package com.drewbitt.trntlist.ui.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.drewbitt.trntlist.R
import com.drewbitt.trntlist.data.model.TrntJson
import com.drewbitt.trntlist.ui.DetailsActivity
import org.jetbrains.anko.startActivity

class RecyclerAdapter(items: List<TrntJson>? = null) : RecyclerView.Adapter<RecyclerAdapter.ListViewHolder>() {

    var items: List<TrntJson> = items ?: emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        // if not doing much, could inflate textview directly
        val view = LayoutInflater.from(parent.context).inflate(R.layout.trnt_item, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        items.getOrNull(position)?.let {
            holder.bind(it)
        }
    }

    class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val nameView: TextView = view.findViewById(R.id.trnt_item_textView)
        private val itemContainer: View = view.findViewById(R.id.item_container)
        private lateinit var mainItem: TrntJson

        init {
            itemContainer.setOnClickListener {
                view.context.startActivity<DetailsActivity>("item" to mainItem)
            }
        }

        fun bind(item: TrntJson) {
            mainItem = item
            nameView.text = item.name
        }
    }
}