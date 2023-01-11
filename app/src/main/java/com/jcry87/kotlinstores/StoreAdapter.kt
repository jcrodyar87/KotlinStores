package com.jcry87.kotlinstores

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.jcry87.kotlinstores.databinding.ItemStoreBinding

class StoreAdapter {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding = ItemStoreBinding.bind(view)
    }

}