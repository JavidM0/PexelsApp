package com.example.presentation.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class CollectionsSpacesItemDecoration(private val mSpace: Int, private val lSpace: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.top = mSpace
        outRect.left = mSpace
        outRect.right = mSpace
        outRect.bottom = mSpace

        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.left = lSpace
        }
    }
}
