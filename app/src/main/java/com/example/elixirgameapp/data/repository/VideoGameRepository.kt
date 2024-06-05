package com.example.elixirgameapp.data.repository

import com.example.elixirgameapp.data.network.api.VideoGameService
import com.example.elixirgameapp.data.response.VideoGameDetailsResponse
import com.example.elixirgameapp.data.response.VideoGameResponse

interface VideoGameRepository {
    /**
     * Fetch the video games from the API
     */
    suspend fun fetchVideoGames(): MutableList<VideoGameResponse>

    suspend fun fetchVideoGameById(idVideoGameService: Long) : VideoGameDetailsResponse

    /**
     * Save the video games to the database
     */

    suspend fun saveAllVideoGameOnDB(videoGames: MutableList<VideoGameResponse>)

    suspend fun getAllVideoGameFromDB(): MutableList<VideoGameResponse>

    suspend fun saveDetailAllVideoGameOnDB(detailVideoGameDetailsResponse: VideoGameDetailsResponse)

    suspend fun getDetailVideoGameFromDB(idVideoFGame: Long): VideoGameDetailsResponse

}