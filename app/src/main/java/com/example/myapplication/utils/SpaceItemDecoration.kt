package com.example.myapplication.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpaceItemDecoration(
    private val space:Int
): RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.bottom = space
        //outRect.left = 20
        /*outRect.right = -30*/
        /*if (parent.getChildLayoutPosition(view) == 0){
            outRect.top = space
        }else{
            outRect.top = 0
        }*/
        //super.getItemOffsets(outRect, view, parent, state)
    }
}