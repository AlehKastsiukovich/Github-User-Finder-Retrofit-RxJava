package by.training.guthubusersearch.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.training.guthubusersearch.R
import by.training.guthubusersearch.adapter.UserAdapter
import by.training.guthubusersearch.repository.SearchRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.loadDataButton
import kotlinx.android.synthetic.main.activity_main.recyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userAdapter = UserAdapter()

        recyclerView.apply {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            addOnScrollListener(object : RecyclerView.OnScrollListener() {

                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    val layoutManager = recyclerView.layoutManager

                    val visibleItemCount = layoutManager?.childCount
                    val totalItemCount = layoutManager?.itemCount
                    val pastVisibleItem = (layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()

                    Log.d("TAG", "visible ${visibleItemCount?.toString()}")
                    Log.d("TAG", "total ${totalItemCount?.toString()}")
                    Log.d("TAG", "pastVisibleItem ${pastVisibleItem?.toString()}")

                    if (pastVisibleItem + 1 == totalItemCount) {
                        loadData()
                    }
                }

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                }
            })

            loadDataButton.setOnClickListener {
                loadData()
            }
        }
    }

    private fun loadData() {
        val result = SearchRepository
            .searchUsers("Belarus")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    userAdapter.update(it.items)
                },
                {
                    Log.d("TAG", it.localizedMessage ?: "")
                }
            )
    }
}
