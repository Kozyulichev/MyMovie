package com.example.mymovie.ui.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mymovie.R
import com.example.mymovie.databinding.SecondFragmentBinding
import com.example.mymovie.model.Film
import com.example.mymovie.model.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.second_fragment.*

class DetailsFragment : Fragment() {

    private var _binding: SecondFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SecondFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val film = arguments?.getParcelable<Result>(BUNDLE_EXTRA)
        if (film!=null){
            binding.detailsName.text = film.title
            binding.detailsDescription.text = film.overview
            Picasso.get().load("$URL_IMAGE${film.poster_path}")
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(details_image)
            binding.reiting.text = film.vote_average.toString()
        }

    }
    companion object {

        const val BUNDLE_EXTRA = "film"

        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

}