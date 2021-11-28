package com.example.movieproject.overview



import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieproject.R
import com.example.movieproject.data.ResultsItem


@BindingAdapter("listData")              //setData
fun bindRecyclerView(recyclerView: RecyclerView, data: List<ResultsItem>?) {

    val adapter = recyclerView.adapter as MovieGridAdapter
    adapter.submitList(data)
}


@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().build()
        Log.e("TAG", "uri:${imgUri}")
        Glide.with(imgView).load("https://image.tmdb.org/t/p/w500/${imgUri}")
            .into(imgView)

    }
}




//
//imgView.load(imgUri) {
//    placeholder(R.drawable.loading_animation)
//    error(R.drawable.ic_broken_image)
//}
//}
//}

@BindingAdapter("MovieApiStatus")
fun bindStatus(statusImageView: ImageView,status: MovieApiStatus?) {
    when (status) {
        MovieApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        MovieApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        MovieApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
        null -> TODO()
    }
}










