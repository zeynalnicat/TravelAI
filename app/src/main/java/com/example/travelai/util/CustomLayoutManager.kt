 package com.example.travelai.util

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CustomLayoutManager(context: Context) : LinearLayoutManager(context) {

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        if (itemCount == 0 || state?.isPreLayout == true) {
            return
        }

        detachAndScrapAttachedViews(recycler!!)

        var top = paddingTop
        var bottom = 0

        for (i in 0 until itemCount) {
            val view = recycler!!.getViewForPosition(i)
            addView(view)

            measureChildWithMargins(view, 0, 0)


            val width = getDecoratedMeasuredWidth(view)
            val height = getDecoratedMeasuredHeight(view)
            val left = paddingLeft
            val right = left + width
            bottom = top + height

            if (i % 2 == 0) {
                bottom *= 2
            }


            layoutDecorated(view, left, top, right, bottom)


            top = bottom
        }
    }
}
