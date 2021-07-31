package com.example.mymovie

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Film(
    val image: Int,
    val name: String,
    val description: String,
    val reting: Double
) :Parcelable

fun getPopularFilms(): List<Film> {
    var films = arrayListOf<Film>()
    films.add(
        Film(
            R.drawable.tihoe_mesto_new,
            "Тихое место 2",
            "474 дня прошло после нападения на Землю охотящихся на звук существ," +
                    " семья Эбботт продолжает бороться за жизнь в полной тишине",6.7
        )
    )
    films.add(
        Film(
            R.drawable.odazhdi_v_gol,
            "Однажды в голливуде",
            "1969 год, золотой век Голливуда уже закончился.",8.0
        )
    )
    return films
}

fun getComedyFilms(): List<Film> {
    var films = arrayListOf<Film>()
    films.add(
        Film(
            R.drawable.tayna_coco,
            "Тайна Коко",
            "12-летний Мигель живёт в мексиканской деревушке в семье сапожников и тайно мечтает стать музыкантом. Тайно, потому что в его семье музыка считается проклятием.",
            9.2
        )
    )
    films.add(
        Film(
            R.drawable.friends,
            "Друзья",
            "Исполнители главных ролей сериала «Друзья» воссоединяются .",9.1
        )
    )
    return films
}


