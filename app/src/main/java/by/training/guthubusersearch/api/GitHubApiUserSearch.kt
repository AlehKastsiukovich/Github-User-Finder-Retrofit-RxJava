package by.training.guthubusersearch.api

import by.training.guthubusersearch.data.Result
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.github.com"

interface GitHubApiUserSearch {

    @GET("search/user")
    fun search(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") per_page: Int
    ): Observable<Result>

    companion object ApiFactory {

        fun createApi(): GitHubApiUserSearch {
            return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
                .create(GitHubApiUserSearch::class.java)
        }
    }
}
