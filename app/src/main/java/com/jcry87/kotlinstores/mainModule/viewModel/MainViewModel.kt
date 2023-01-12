package com.jcry87.kotlinstores.mainModule.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jcry87.kotlinstores.StoreApplication
import com.jcry87.kotlinstores.common.entities.StoreEntity

class MainViewModel: ViewModel() {
    private var stores: MutableLiveData<List<StoreEntity>>

    init {
        stores = MutableLiveData()
        loadStores()
    }

    fun getStores() : LiveData<List<StoreEntity>>{
        return stores
    }
    private fun loadStores(){
        Thread {
            val storesList = StoreApplication.database.storeDao().getAllStores()
            stores.value = storesList
        }.start()
    }
}