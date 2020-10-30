package by.training.guthubusersearch.repository

import by.training.guthubusersearch.api.GitHubApiUserSearch
import by.training.guthubusersearch.data.Result
import io.reactivex.Observable

class SearchRepository(private val api: GitHubApiUserSearch) {
    fun searchUsers(location: String, language: String): Observable<Result> {
        return api.search(query = "location:$location+language:$language", page = 1, per_page = 100)
    }
}
