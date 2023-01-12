package com.jcry87.kotlinstores.mainModule.model

import com.jcry87.kotlinstores.StoreApplication
import com.jcry87.kotlinstores.common.entities.StoreEntity

class MainInteractor {
    interface StoresCallback{
        fun getStoresCallback(stores: MutableList<StoreEntity>)
    }
    fun getStoresCallback(callback: StoresCallback){
        Thread {
            val storesList = StoreApplication.database.storeDao().getAllStores()
            callback.getStoresCallback(storesList)
        }.start()
    }
}