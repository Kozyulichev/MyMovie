package com.example.mymovie.ui.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.example.mymovie.R
import com.example.mymovie.model.Film
import com.example.mymovie.model.MovieDTO
import com.example.mymovie.model.Result

class ComedyFilmAdapter(private var omItemViewClickListener: (Result)->Unit) :
    RecyclerView.Adapter<ComedyFilmAdapter.Holder>() {

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
        holder.onBind(films[position], position)

    }

    override fun getItemCount(): Int {
        return films.size
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var filmImage: ImageView? = null
        var filmName: TextView? = null
        var descriptionFilm: TextView? = null

        init {
            filmImage = itemView.findViewById(R.id.popular_film_image)
            filmName = itemView.findViewById(R.id.popular_film_name)
        }

        fun onBind(film: Result, position: Int) {
            filmImage?.setImageResource(R.drawable.friends)
            filmName?.text = film.title
            descriptionFilm?.text = film.overview

            itemView.setOnClickListener { omItemViewClickListener.invoke(film) }


        }
    }
}
