package com.example.moviesapplication

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapplication.models.HomeFeed
import com.example.moviesapplication.models.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_row.view.*

class MainAdapter(private val homeFeed: HomeFeed) : RecyclerView.Adapter<CustomViewHolder>() {

    companion object {
        const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w185"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.movie_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun getItemCount(): Int {
        return homeFeed.results.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val movie = homeFeed.results[position]
        val posterPath = movie.poster_path
        val posterImageUrl = "${IMAGE_BASE_URL}${posterPath}"
        val moviePosterImageView = holder.view.imageView_movie_poster

        Picasso.get().load(posterImageUrl).into(moviePosterImageView)

        holder.view.textView_movie_title.text = movie.title
//        TODO("retrieve director")
//        TODO("do not concat")
        holder.view.textView_movie_details.text = movie.release_date.substring(0..3) + " * " + "Director Name"
        holder.view.textView_movie_rating.text = movie.vote_average + "/10"

    }

}

class CustomViewHolder(val view: View, var movie: Movie? = null) : RecyclerView.ViewHolder(view) {

    companion object {
        const val MOVIE_TITLE_KEY = "MOVIE_TITLE"
        const val MOVIE_ID_KEY = "MOVIE_ID"
        const val MOVIE_DIRECTOR_KEY = "MOVIE_DIRECTOR"
        const val MOVIE_DESCRIPTION_KEY = "MOVIE_DESCRIPTION"
        const val MOVIE_RELEASE_DATE_KEY = "MOVIE_RELEASE_DATE"
    }

    init {
        view.setOnClickListener {
            val intent = Intent(view.context, MovieDetailsActivity::class.java)

            intent.putExtra(MOVIE_ID_KEY, movie?.id)
            intent.putExtra(MOVIE_TITLE_KEY, movie?.title)
            intent.putExtra(MOVIE_RELEASE_DATE_KEY, movie?.release_date)
            intent.putExtra(MOVIE_DESCRIPTION_KEY, movie?.overview)
//            TODO("send director")

            view.context.startActivity(intent)
        }
    }

}
