package com.example.moviesapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapplication.models.MovieDetail
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MovieDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        recyclerView_main.layoutManager = LinearLayoutManager(this)

        fetchJson()

    }

    private fun fetchJson() {
        val movieId = intent.getIntExtra(CustomViewHolder.MOVIE_ID_KEY, -1)

        val movieDetailsUrl =
            "https://api.themoviedb.org/3/movie/${movieId}?api_key=${MainActivity.API_KEY}"
        val client = OkHttpClient()
        val request = Request.Builder().url(movieDetailsUrl).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                val gson = GsonBuilder().create()
                val movieDetails = gson.fromJson(body, MovieDetail::class.java)

                runOnUiThread {
                    recyclerView_main.adapter = MovieDetailsAdapter(movieDetails)
                }
            }
        })

    }
}