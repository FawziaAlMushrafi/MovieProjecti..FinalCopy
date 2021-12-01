package com.example.movieproject

import android.content.Intent
import android.net.Uri
import android.view.Menu
import android.view.MenuItem
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.movieproject.databinding.FragmentMovieListBinding
import com.example.movieproject.network.MovieApiFilter
import com.example.movieproject.overview.MovieGridAdapter
import com.example.movieproject.overview.ViewModel
import java.util.zip.GZIPOutputStream

// OUR FINAL WORK

class MovieListFragment : Fragment() {

    private val viewModel: ViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMovieListBinding.inflate(inflater)
        setHasOptionsMenu(true)


        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel

        // Sets the adapter of the photosGrid RecyclerView
        binding.photosGrid.adapter = MovieGridAdapter()

        return binding.root

        binding
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.movie_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.SHOW_ALL -> viewModel.getMovies()
            R.id.action -> viewModel.getMovieFilter(MovieApiFilter.SHOW_ACTION)
            R.id.adventure -> viewModel.getMovieFilter(MovieApiFilter.SHOW_ADVENTURE)
            R.id.animation -> viewModel.getMovieFilter(MovieApiFilter.SHOW_ANIMATION)

        }
        return true
    }


}





