package com.jcry87.kotlinstores.common.utils

import com.jcry87.kotlinstores.common.entities.StoreEntity

interface MainAux {
    fun hideFab(isVisible: Boolean = false)
    fun addStore(storeEntity: StoreEntity)
    fun updateStore(storeEntity: StoreEntity)
}