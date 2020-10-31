package by.training.guthubusersearch.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.training.guthubusersearch.R
import by.training.guthubusersearch.adapter.CustomScrollListener
import by.training.guthubusersearch.adapter.UserAdapter
import by.training.guthubusersearch.repository.SearchRepository
import kotlinx.android.synthetic.main.activity_main.loadDataButton
import kotlinx.android.synthetic.main.activity_main.recyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var userAdapter: UserAdapter
    private lateinit var scrollListener: RecyclerView.OnScrollListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userAdapter = UserAdapter()
        scrollListener = CustomScrollListener(userAdapter)

        recyclerView.apply {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            addOnScrollListener(scrollListener)

            loadDataButton.setOnClickListener {
                SearchRepository.loadData(userAdapter)
            }
        }
    }
}
