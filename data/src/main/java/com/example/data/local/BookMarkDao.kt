package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.data.local.BookMarkEntity.Companion.BOOK_MARK_TABLE_NAME

@Dao
interface BookMarkDao {
    @Insert
    fun insertBookMark(entity: BookMarkEntity)

    @Query("SELECT * FROM $BOOK_MARK_TABLE_NAME WHERE uid = :id ")
    fun getImageWithId(id: Int): BookMarkEntity?

    @Query("SELECT * FROM $BOOK_MARK_TABLE_NAME")
    fun getAllBookMarks(): List<BookMarkEntity>
}
