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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

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
            R.id.Comedy -> viewModel.getMovieFilter(MovieApiFilter.SHOW_COMEDY)
            R.id.Crime -> viewModel.getMovieFilter(MovieApiFilter.SHOW_CRIME)
            R.id.Drama -> viewModel.getMovieFilter(MovieApiFilter.SHOW_DRAMA)
            R.id.Documentary -> viewModel.getMovieFilter(MovieApiFilter.SHOW_DOCUMENTARY)
            R.id.Family -> viewModel.getMovieFilter(MovieApiFilter.SHOW_FAMILY)
            R.id.Fantasy -> viewModel.getMovieFilter(MovieApiFilter.SHOW_FANTASY)
            R.id.History -> viewModel.getMovieFilter(MovieApiFilter.SHOW_HISTORY)
            R.id.Horror -> viewModel.getMovieFilter(MovieApiFilter.SHOW_HORROR)
            R.id.Music -> viewModel.getMovieFilter(MovieApiFilter.SHOW_MUSIC)
            R.id.Mystery -> viewModel.getMovieFilter(MovieApiFilter.SHOW_MYSTERY)
            R.id.Romance -> viewModel.getMovieFilter(MovieApiFilter.SHOW_ROMANCE)
            R.id.Science_Fiction -> viewModel.getMovieFilter(MovieApiFilter.SHOW_SCIENCE_FICTION)
            R.id.TV_Movie -> viewModel.getMovieFilter(MovieApiFilter.SHOW_TV_MOVIE)
            R.id.Thriller -> viewModel.getMovieFilter(MovieApiFilter.SHOW_THRILLER)
            R.id.War -> viewModel.getMovieFilter(MovieApiFilter.SHOW_WAR)
            R.id.Western -> viewModel.getMovieFilter(MovieApiFilter.SHOW_WESTERN)


            R.id.sort -> viewModel.sort()

        }
        return true
    }


}





