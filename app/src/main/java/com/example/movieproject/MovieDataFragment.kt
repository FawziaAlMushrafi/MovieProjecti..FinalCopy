package com.example.movieproject

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*

import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.movieproject.databinding.FragmentMovieDataBinding
import com.example.movieproject.network.MovieApiFilter
import com.example.movieproject.overview.MovieGridAdapter
import com.example.movieproject.overview.ViewModel


class MovieDataFragment : Fragment() {


    private val viewModel: ViewModel by activityViewModels()
    var position:Int = 0
    private var movielink = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
                setHasOptionsMenu(true)

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
    private fun onShare() {
            val intent = Intent(Intent.ACTION_SEND)
                .putExtra(Intent.EXTRA_TEXT,"I'm currently watching ${viewModel.title.value} ,link : https://www.themoviedb.org/movie/${viewModel.movieid.value}")
                .setType("text/plain")
            if (activity?.packageManager?.resolveActivity(intent, 0) != null) {
                startActivity(intent)

        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.share_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.shareMenuButton -> {
                onShare()
                return true
            }
        else -> return super.onOptionsItemSelected(item)
        }
    }

}
