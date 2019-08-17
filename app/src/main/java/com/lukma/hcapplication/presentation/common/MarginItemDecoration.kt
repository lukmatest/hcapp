package com.lukma.hcapplication.presentation.common

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MarginItemDecoration(
    private val spaceHeight: Int,
    private val mode: Mode = Mode.List()
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            val position = parent.getChildAdapterPosition(view)
            when (mode) {
                is Mode.List -> {
                    if (mode.orientation == Orientation.VERTICAL) {
                        if (position == 0) {
                            top = spaceHeight
                        }
                        left = spaceHeight
                        right = spaceHeight
                    } else {
                        top = spaceHeight
                        if (mode.direction == Direction.LTR && position != 0) {
                            left = spaceHeight
                        }
                        if (mode.direction == Direction.RTL && position != 0) {
                            right = spaceHeight
                        }
                    }
                    bottom = spaceHeight
                }
                is Mode.Grid -> {
                    if (position < mode.span) {
                        top = spaceHeight
                    }
                    left = if (position % mode.span == 0) spaceHeight else spaceHeight / mode.span
                    right = if (position % mode.span != 0) spaceHeight else spaceHeight / mode.span
                    bottom = spaceHeight
                }
            }
        }
    }

    sealed class Mode {
        data class List(
            val orientation: Orientation = Orientation.VERTICAL,
            val direction: Direction = Direction.LTR
        ) : Mode()

        data class Grid(val span: Int) : Mode()
    }

    enum class Orientation {
        VERTICAL,
        HORIZONTAL
    }

    enum class Direction {
        RTL,
        LTR
    }
}
