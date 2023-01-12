package com.jcry87.kotlinstores.common.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jcry87.kotlinstores.common.database.StoreDao
import com.jcry87.kotlinstores.common.entities.StoreEntity

@Database(entities = arrayOf(StoreEntity::class), version = 3)
abstract class StoreDatabase : RoomDatabase() {
    abstract fun storeDao() : StoreDao
}