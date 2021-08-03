package com.example.mymovie.ui.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovie.*
import com.example.mymovie.databinding.MainFragmentBinding
import com.example.mymovie.viewModel.AppState
import com.example.mymovie.viewModel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    private val lambdaNewView = { film: Film ->
        val manager = activity?.supportFragmentManager
        if (manager != null) {
            val bundle = Bundle()
            bundle.putParcelable(DetailsFragment.BUNDLE_EXTRA, film)
            manager.beginTransaction()
                .add(R.id.container, DetailsFragment.newInstance(bundle))
                .addToBackStack(null)
                .commitAllowingStateLoss()
        }
    }


    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!


    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var comedyRecyclerView: RecyclerView

    private val comedyFilmAdapter = ComedyFilmAdapter(lambdaNewView)
    private val filmAdapter = PopularFilmAdapter(lambdaNewView)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.getFilmsFromLocalSource()

    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val popularFilmData = appState.popularFilmData
                val comedyFilmData = appState.comedyFilmData
                loadingLayout.visibility = View.GONE
                setPopularFilmData(popularFilmData)
                setComedyFilmData(comedyFilmData)
            }
            is AppState.Loading -> {
                loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                loadingLayout.visibility = View.GONE
                Snackbar
                    .make(binding.mainView, "Error", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Reload") { viewModel.getFilmsFromLocalSource() }
                    .show()
            }

        }
    }

    @SuppressLint("WrongConstant")
    private fun setComedyFilmData(comedyFilmData: List<Film>) {
        comedyRecyclerView = binding.recyclerComedyFilm
        comedyFilmAdapter.setFilms(comedyFilmData)
        comedyRecyclerView.adapter = comedyFilmAdapter
        val lm = LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)
        comedyRecyclerView.layoutManager = lm

    }

    @SuppressLint("WrongConstant")
    private fun setPopularFilmData(film: List<Film>) {
        recyclerView = binding.recyclerPopularFilm
        filmAdapter.setFilms(film)
        recyclerView.adapter = filmAdapter
        val lm = LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)
        recyclerView.layoutManager = lm

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = MainFragment()
    }

}