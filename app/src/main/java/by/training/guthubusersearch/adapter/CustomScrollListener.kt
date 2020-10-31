package by.training.guthubusersearch.adapter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.training.guthubusersearch.repository.SearchRepository

class CustomScrollListener(private val adapter: UserAdapter) : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val layoutManager = recyclerView.layoutManager

        val totalItemCount = layoutManager?.itemCount
        val pastVisibleItem = (layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()

        if (pastVisibleItem + 1 == totalItemCount) {
            SearchRepository.loadData(adapter)
        }
    }
}
