package com.example.mymovie.ui.view.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovie.R
import com.example.mymovie.model.Film
import com.example.mymovie.model.Result

class PopularFilmAdapter(private var omItemViewClickListener: (Result) -> Unit) :
    RecyclerView.Adapter<PopularFilmAdapter.Holder>() {

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

        init {
            with(itemView) {
                filmImage = findViewById(R.id.popular_film_image)
                filmName = findViewById(R.id.popular_film_name)
            }
        }

        fun onBind(film: Film, position: Int) {

            var filmsss = films[position]
            var image = filmsss.image
            filmImage?.setImageResource(image)
            filmName?.text = film.name

            itemView.setOnClickListener { omItemViewClickListener.invoke(film) }
        }
    }
}
