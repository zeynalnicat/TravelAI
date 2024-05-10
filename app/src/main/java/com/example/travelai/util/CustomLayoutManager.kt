package com.example.travelai.util
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class CustomLayoutManager(private val context: Context) : StaggeredGridLayoutManager(2, VERTICAL) {

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        if (itemCount == 0 || state?.isPreLayout == true) {
            return
        }

        recycler?.let {
            detachAndScrapAttachedViews(it)

            val columnWidth = width / 2
            val columnHeights = IntArray(2)

            for (i in 0 until itemCount) {
                val viewType = i % 2
                val view = it.getViewForPosition(i)

                val lp = view.layoutParams as RecyclerView.LayoutParams

                if (viewType == VIEW_TYPE_LEFT) {
                    lp.height = 2 * columnWidth
                } else {
                    lp.height = columnWidth
                }

                lp.width = columnWidth

                addView(view)

                measureChildWithMargins(view, 0, 0)

                val column = if (viewType == VIEW_TYPE_LEFT) 0 else 1
                val top = columnHeights[column] ?: 0
                val bottom = top + lp.height

                layoutDecoratedWithMargins(view, columnWidth * column, top, columnWidth * (column + 1), bottom)

                columnHeights[column] = bottom
            }
        }
    }

    companion object {
        const val VIEW_TYPE_LEFT = 0
        const val VIEW_TYPE_RIGHT = 1
    }
}
