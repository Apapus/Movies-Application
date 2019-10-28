package com.example.moviesapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapplication.models.MovieDetail
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_details.view.*
import kotlinx.android.synthetic.main.movie_row.view.*

class MovieDetailsAdapter(private val movieDetail: MovieDetail?) : RecyclerView.Adapter<MovieDetailsViewHolder>() {

    override fun onBindViewHolder(holder: MovieDetailsViewHolder, position: Int) {

        holder.customView.textView_movie_title.text = CustomViewHolder.MOVIE_TITLE_KEY
        holder.customView.textView_details_movie_duration.text = CustomViewHolder.MOVIE_RELEASE_DATE_KEY + " * " + movieDetail?.runtime
        holder.customView.textView_details_movie_description.text = CustomViewHolder.MOVIE_DESCRIPTION_KEY

        val moviePoster = holder.customView.imageView_movie_poster
        val posterUrl = MainAdapter.IMAGE_BASE_URL + movieDetail?.poster_path
        Picasso.get().load(posterUrl).into(moviePoster)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieDetailsViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val customView = layoutInflater.inflate(R.layout.movie_row, parent, false)

        return MovieDetailsViewHolder(customView)
    }

    override fun getItemCount(): Int {
        return 1
    }


}

class MovieDetailsViewHolder(val customView: View) : RecyclerView.ViewHolder(customView) {

}
