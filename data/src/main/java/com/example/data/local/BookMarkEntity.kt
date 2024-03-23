package com.example.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.local.BookMarkEntity.Companion.BOOK_MARK_TABLE_NAME

@Entity(tableName = BOOK_MARK_TABLE_NAME)
data class BookMarkEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "photographer") val photographer: String
) {
    companion object {
        const val BOOK_MARK_TABLE_NAME = "book_mark"
    }
}
