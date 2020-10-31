package by.training.guthubusersearch.repository

import android.util.Log
import by.training.guthubusersearch.adapter.UserAdapter
import by.training.guthubusersearch.api.GitHubApiUserSearch
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object SearchRepository {

    private val api = GitHubApiUserSearch.createApi()
    private var currentPage: Int = 1

    fun searchUsers(adapter: UserAdapter, location: String) {
        val result = api.search(location, currentPage++, 6)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    adapter.update(it.items)
                },
                {
                    Log.d("TAG", it.localizedMessage ?: "")
                }
            )
    }
}
