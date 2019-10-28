package com.example.moviesapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapplication.models.HomeFeed
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    companion object {
        const val API_KEY = "282cab096ab0e71db8e49f763f6c9a94"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView_main.layoutManager = LinearLayoutManager(this)

        fetchJson()

        // TODO("fetch director name")
    }

    private fun fetchJson() {
        /*TODO("load next page when scroll down")*/
        val url = "https://api.themoviedb.org/3/movie/top_rated?api_key=${API_KEY}&language=en-US&page=1"
        val requestTopRated = Request.Builder().url(url).build()
        val clientTopRated = OkHttpClient()

        clientTopRated.newCall(requestTopRated).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                val gson = GsonBuilder().create()
                val homeFeed = gson.fromJson(body, HomeFeed::class.java)

                runOnUiThread {
                    recyclerView_main.adapter = MainAdapter(homeFeed)
                }
            }
        })
    }
}
