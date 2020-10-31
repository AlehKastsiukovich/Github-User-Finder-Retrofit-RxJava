package by.training.guthubusersearch.repository

import by.training.guthubusersearch.api.GitHubApiUserSearch
import by.training.guthubusersearch.data.Result
import io.reactivex.Observable

object SearchRepository {

    private val api = GitHubApiUserSearch.createApi()
    private var currentPage: Int = 1

    fun searchUsers(location: String): Observable<Result> {
        return api.search(location, currentPage++, 6)
    }
}
