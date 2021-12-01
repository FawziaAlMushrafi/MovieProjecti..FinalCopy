package com.example.movieproject

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.movieproject.databinding.FragmentMovieDataBinding
import com.example.movieproject.overview.MovieGridAdapter
import com.example.movieproject.overview.ViewModel


class MovieDataFragment : Fragment() {


    private val viewModel: ViewModel by activityViewModels()
    var position:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            position= it!!.getInt("MovieId")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.movieInfo(position)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.themoviedb.org/"))
//        startActivity(i)
        val binding = FragmentMovieDataBinding.inflate(inflater)



        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        binding.shareviewmodel = viewModel

        // Sets the adapter of the photosGrid RecyclerView
//        binding.photosGrid.adapter = MovieGridAdapter()

        return binding.root
    }


}
