package by.training.guthubusersearch.repository

import android.util.Log
import by.training.guthubusersearch.adapter.UserAdapter
import by.training.guthubusersearch.api.GitHubApiUserSearch
import by.training.guthubusersearch.data.Result
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object SearchRepository {

    private val api = GitHubApiUserSearch.createApi()
    private var currentPage: Int = 1

    private fun searchUsers(location: String): Observable<Result> {
        return api.search(location, currentPage++, 6)
    }

    fun loadData(adapter: UserAdapter) {
        val result = searchUsers("Belarus")
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
