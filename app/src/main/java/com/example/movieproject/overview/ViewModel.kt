package com.example.movieproject.overview


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieproject.data.ResultsItem
import com.example.movieproject.network.MovieApi
import com.example.movieproject.network.MovieApiFilter
import kotlinx.coroutines.launch

enum class MovieApiStatus { LOADING, ERROR, DONE }


class ViewModel : ViewModel() {

    private val _status = MutableLiveData<MovieApiStatus>()

    // The external immutable LiveData for the request status
    val status: LiveData<MovieApiStatus> = _status

    // Internally, we use a MutableLiveData, because we will be updating the List of MarsPhoto
    // with new values
    private val _photos = MutableLiveData<List<ResultsItem>>()

    // The external LiveData interface to the property is immutable, so only this class can modify
    val photos: LiveData<List<ResultsItem>> = _photos
    var title = MutableLiveData<String>()
    var detail = MutableLiveData<String>()
    var poster = MutableLiveData<String>()
    var rating = MutableLiveData<String>()
    var language = MutableLiveData<String>()
    var genre = MutableLiveData<String>()
    var backposter = MutableLiveData<String>()
    var movieid = MutableLiveData<Int>()
    var release_date = MutableLiveData<String>()


    init {
        getMovies()
    }

     fun getMovies() {

        // here lodong
        _status.value = MovieApiStatus.LOADING
        viewModelScope.launch {

            try {
                //MovieApi.retrofitService.getPhotos()
                _photos.value = MovieApi.retrofitService.getMovies().results

                _status.value = MovieApiStatus.DONE
            } catch (e: Exception) {
                _status.value = MovieApiStatus.ERROR
//                _photos.value = listOf()
            }
        }
    }


     fun getMovieFilter(filter: MovieApiFilter) {

        // here lodong
        _status.value = MovieApiStatus.LOADING
        viewModelScope.launch {

            try {
                //MovieApi.retrofitService.getPhotos()
                _photos.value = MovieApi.retrofitService.getMovieFilter(filter.value).results

                _status.value = MovieApiStatus.DONE
            } catch (e: Exception) {
                _status.value = MovieApiStatus.ERROR
//                _photos.value = listOf()
            }
        }
    }
    fun movieInfo(index: Int) {
        val item = _photos.value?.get(index)
        title.value = item?.title
        detail.value = item?.overview
        poster.value = item?.posterPath
        rating.value = item?.voteAverage.toString()
        language.value = item?.originalLanguage
        genre.value = item?.genreIds.toString()
        backposter.value = item?.backdropPath
        movieid.value = item?.id
        release_date.value = item?.releaseDate
    }


}
