package com.example.data.local

import com.example.data.local.mapper.toEntity
import com.example.data.local.mapper.toImage
import com.example.domain.bookmark.repository.BookMarkRepository
import com.example.domain.image.model.Image

class BookMarkRepositoryImpl(
    private val bookMarkDao: BookMarkDao
) : BookMarkRepository {

    override fun getBookMarks(): List<Image> =
        bookMarkDao.getAllBookMarks().map {
            it.toImage()
        }

    override fun saveBookMarks(image: Image) =
        bookMarkDao.insertBookMark(image.toEntity())

    override fun getBookMarkWithId(id: Int): Image? {
        return bookMarkDao.getImageWithId(id)?.toImage()
    }
}
