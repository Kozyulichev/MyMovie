package com.example.mymovie.ui.view.news

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mymovie.R

// В этом фрагменте я хотел вставить какое-то нормальное
// api с новостями о фильмах, но не нашел ни одного такого апи(
// пока не добрался до реализации этого фрагмента
// думаю сделать его просто webView
class FavouriteFragment : Fragment() {

    companion object {
        fun newInstance() = FavouriteFragment()
    }

    private lateinit var viewModel: FavouriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favourite_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FavouriteViewModel::class.java)
        // TODO: Use the ViewModel
    }

}