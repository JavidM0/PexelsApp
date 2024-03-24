package com.example.domain.bookmark.repository

import com.example.domain.image.model.Image

interface BookMarkRepository {

    fun getBookMarks(): List<Image>
    fun saveBookMarks(image: Image)
    fun getBookMarkWithId(id: Int): Image?
}
