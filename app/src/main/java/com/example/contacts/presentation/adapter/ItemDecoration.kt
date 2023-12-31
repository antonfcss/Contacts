package com.example.contacts.presentation.adapter

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts.R

class ItemDecoration(private val divider: Int, context: Context) :
    RecyclerView.ItemDecoration() {
    private val mDivider: Drawable = ContextCompat.getDrawable(context, R.drawable.line_divider)!!

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        with(outRect) {
            left = divider
            right = divider
            bottom = divider
        }
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight
        val childCount = parent.childCount
        for (i in 0 until childCount - 1) {
            val child: View = parent.getChildAt(i)
            val nextChild: View = parent.getChildAt(i + 1)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val nextParams = nextChild.layoutParams as RecyclerView.LayoutParams
            val top =
                child.bottom + params.bottomMargin +
                        (nextChild.top - child.bottom - params.bottomMargin - nextParams.topMargin) / 2
            val bottom = top + mDivider.intrinsicHeight
            mDivider.setBounds(left, top, right, bottom)
            mDivider.draw(c)
        }
    }


}