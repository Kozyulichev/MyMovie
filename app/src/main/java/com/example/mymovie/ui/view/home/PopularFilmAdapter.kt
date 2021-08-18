package com.example.mymovie.ui.view.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovie.R
import com.example.mymovie.model.MovieDTO
import com.example.mymovie.model.Result
import com.squareup.picasso.Picasso

class PopularFilmAdapter(private var omItemViewClickListener: (Result) -> Unit) :
    RecyclerView.Adapter<PopularFilmAdapter.Holder>() {

    private var films: List<Result> = listOf()

    fun setFilms(data: MovieDTO) {
        films = data.results
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        var v = LayoutInflater.from(parent.context)
            .inflate(R.layout.popular_film_list, parent, false)
        return Holder(v)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.onBind(films[position])

    }

    override fun getItemCount(): Int {
        return films.size
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var filmImage: ImageView? = null
        var filmName: TextView? = null

        init {
            with(itemView) {
                filmImage = findViewById(R.id.popular_film_image)
                filmName = findViewById(R.id.popular_film_name)
            }
        }

        fun onBind(film: Result) {
            Picasso.get().load("$URL_IMAGE${film.poster_path}")
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(filmImage)
            filmName?.text = film.title
            itemView.setOnClickListener { omItemViewClickListener.invoke(film) }
        }
    }
}
