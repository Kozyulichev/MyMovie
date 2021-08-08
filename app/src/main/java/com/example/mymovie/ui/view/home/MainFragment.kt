package com.example.mymovie.ui.view.home

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovie.R
import com.example.mymovie.databinding.MainFragmentBinding
import com.example.mymovie.model.MovieDTO
import com.example.mymovie.model.Result
import com.example.mymovie.viewModel.AppState
import com.example.mymovie.viewModel.MainViewModel
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!


    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var comedyRecyclerView: RecyclerView


    private val lambdaNewViewInternet = { film: Result ->
        val manager = activity?.supportFragmentManager
        if (manager != null) {
            val bundle = Bundle()
            bundle.putParcelable(DetailsFragment.BUNDLE_EXTRA, film)
            manager.beginTransaction()
                .replace(R.id.nav_host_fragment_activity_main, DetailsFragment.newInstance(bundle))
                .addToBackStack(null)
                .commitAllowingStateLoss()
        }
    }
    private val comedyFilmAdapter = ComedyFilmAdapter(lambdaNewViewInternet)
    private val filmAdapter = PopularFilmAdapter(lambdaNewViewInternet)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.getMovieFromRemoteDataSource("ru-RU")
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val popularFilmData = appState.popularFilmData
                val comedyFilmData = appState.popularFilmData
                loadingLayout.visibility = View.GONE
                setComedyFilmData(popularFilmData)
                setPopularFilmData(comedyFilmData)
            }
            is AppState.Loading -> {
                loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                loadingLayout.visibility = View.GONE
                /*Snackbar
                    .make(binding.mainView, "Error", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Reload") { viewModel.getFilmsFromLocalSource() }
                    .show()*/
            }

        }
    }

    @SuppressLint("WrongConstant")
    private fun setComedyFilmData(comedyFilmData: MovieDTO) {
        comedyRecyclerView = binding.recyclerComedyFilm
        comedyFilmAdapter.setFilms(comedyFilmData)
        comedyRecyclerView.adapter = comedyFilmAdapter
        val lm = LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)
        comedyRecyclerView.layoutManager = lm

    }

    @SuppressLint("WrongConstant")
    private fun setPopularFilmData(film: MovieDTO) {
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