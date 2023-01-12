package com.jcry87.kotlinstores

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(StoreEntity::class), version = 3)
abstract class StoreDatabase : RoomDatabase() {
    abstract fun storeDao() : StoreDao
}