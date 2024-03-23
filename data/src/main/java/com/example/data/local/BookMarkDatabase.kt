package com.example.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

private const val DATABASE_VERSION = 1

@Database(entities = [BookMarkEntity::class], version = DATABASE_VERSION)
abstract class BookMarkDatabase : RoomDatabase() {

    abstract fun bookMarkDao(): BookMarkDao
}
