package com.example.movieproject.network

import com.example.movieproject.data.Response
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.themoviedb.org/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


interface MovieApiService {

    @GET("3/movie/popular?api_key=b4c97cb2cc882e69aa3c284a354e2cad")
    suspend fun getMovies(): Response

    @GET("3/movie/popular?api_key=b4c97cb2cc882e69aa3c284a354e2cad")
    suspend fun getMovieFilter(@Query("with_genres")type:Int): Response

}

object MovieApi {
    val retrofitService: MovieApiService by lazy { retrofit.create(MovieApiService::class.java) }
}
enum class MovieApiFilter(val value: Int){
    SHOW_ACTION(28 ),
    SHOW_ADVENTURE(12),
    SHOW_ANIMATION(16)

}