package by.training.guthubusersearch.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import by.training.guthubusersearch.R
import by.training.guthubusersearch.api.GitHubApiUserSearch
import by.training.guthubusersearch.repository.SearchRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val result = SearchRepository(GitHubApiUserSearch.createApi())
            .searchUsers("Belarus", "Kotlin")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    Log.d("TAG", "${it.items[0]}")
                },
                {
                    Log.d("TAG", "Err")
                    it.printStackTrace()
                }
            )
    }
}
