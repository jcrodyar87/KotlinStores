package com.jcry87.kotlinstores.mainModule.model

import com.jcry87.kotlinstores.StoreApplication
import com.jcry87.kotlinstores.common.entities.StoreEntity

class MainInteractor {

    fun getStores(callback: (MutableList<StoreEntity>) -> Unit){
       Thread {
            val storesList = StoreApplication.database.storeDao().getAllStores()
            callback(storesList)
        }.start()
    }

    fun deleteStore(storeEntity: StoreEntity, callback: (StoreEntity) -> Unit){
        Thread {
            StoreApplication.database.storeDao().deleteStore(storeEntity)
            callback(storeEntity)
        }.start()
    }

    fun updateStore(storeEntity: StoreEntity, callback: (StoreEntity) -> Unit){
        Thread {
            StoreApplication.database.storeDao().updateStore(storeEntity)
            callback(storeEntity)
        }.start()
    }
}