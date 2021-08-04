package com.example.mymovie.ui.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.example.mymovie.R
import com.example.mymovie.model.Film

class ComedyFilmAdapter(private var omItemViewClickListener: (Film)->Unit) :
    RecyclerView.Adapter<ComedyFilmAdapter.Holder>() {

    private var films: List<Film> = listOf()

    fun setFilms(data: List<Film>) {
        films = data
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
            descriptionFilm = itemView.findViewById(R.id.popular_film_description)
        }

        fun onBind(film: Film, position: Int) {
            var filmsss = films[position]
            var image = filmsss.image
            filmImage?.setImageResource(image)
            filmName?.text = film.name
            descriptionFilm?.text = film.description

            itemView.setOnClickListener { omItemViewClickListener.invoke(film) }


        }
    }
}
