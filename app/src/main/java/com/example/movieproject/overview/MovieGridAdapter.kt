package com.example.movieproject.overview


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieproject.MovieListFragment
import com.example.movieproject.MovieListFragmentDirections
import com.example.movieproject.R
import com.example.movieproject.data.Response
import com.example.movieproject.data.ResultsItem
import com.example.movieproject.databinding.GridViewItemBinding

class MovieGridAdapter : ListAdapter<ResultsItem, MovieGridAdapter.MovieViewHolder>(DiffCallback) {

    class MovieViewHolder(

        private var binding: GridViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {


        fun bind(Movieitem: ResultsItem) {
            //list_Item = photo


            binding.movieList = Movieitem

            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }

        var pointer = binding.button
    }

    companion object DiffCallback : DiffUtil.ItemCallback<ResultsItem>() {
        override fun areItemsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
            return oldItem.posterPath == newItem.posterPath
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        return MovieViewHolder(
            GridViewItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val moviePhoto = getItem(position)
        holder.bind(moviePhoto)




        holder.pointer.setOnClickListener {
            val action = MovieListFragmentDirections.actionMovieListFragmentToMovieDataFragment(position)
            holder.itemView.findNavController().navigate(action)
        }
    }
}


