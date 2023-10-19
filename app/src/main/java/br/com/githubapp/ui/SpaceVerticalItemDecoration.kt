package br.com.githubapp.ui

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import br.com.githubapp.dp

class SpaceVerticalItemDecoration(
    private val space: Int
    ) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        val itemCount = parent.adapter?.itemCount ?: 0

        when (position) {
            0 -> {
                outRect.top = space.dp
                outRect.bottom = (space / 2).dp
            }
            itemCount - 1 -> {
                outRect.top = (space / 2).dp
                outRect.bottom = space.dp
            }
            else -> {
                outRect.top = space.dp
                outRect.bottom = space.dp
            }
        }

    }
}