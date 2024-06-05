package com.example.elixirgameapp.data.repository

import com.example.elixirgameapp.data.local.dao.VideoGameDao
import com.example.elixirgameapp.data.network.api.VideoGameService
import com.example.elixirgameapp.data.response.VideoGameDetailsResponse
import com.example.elixirgameapp.data.response.VideoGameResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Implementations of the video games API REST methods in the interface
 */

class VideoGameRepositoryImpl(
    private var apiservice: VideoGameService,
    private var daoDB: VideoGameDao
) : VideoGameRepository {
    override suspend fun fetchVideoGames(): MutableList<VideoGameResponse> {
        return withContext(Dispatchers.IO) {
            val listVideoGames = apiservice.getAllVideoGames()
            listVideoGames
        }
    }

    override suspend fun fetchVideoGameById(idVideoGameService: Long): VideoGameDetailsResponse {
        return withContext(Dispatchers.IO) {
            val videoGameDetails = apiservice.getVideoGameById(idVideoGameService)
            videoGameDetails
        }
    }

    /**
     * Implementations of the querys from a DAO
     */

    override suspend fun saveAllVideoGameOnDB(videoGames: MutableList<VideoGameResponse>) {
        return withContext(Dispatchers.IO){
            daoDB.insertVideoGames(videoGames)
        }
    }

    override suspend fun getAllVideoGameFromDB(): MutableList<VideoGameResponse> {
        return withContext(Dispatchers.IO) {
            val response = daoDB.getAllVideoGames()
            response
        }
    }

    override suspend fun saveDetailAllVideoGameOnDB(detailVideoGameDetailsResponse: VideoGameDetailsResponse) {
        return withContext(Dispatchers.IO) {
            daoDB.insertVideoGamesDetails(detailVideoGameDetailsResponse)
        }
    }

    override suspend fun getDetailVideoGameFromDB(idVideoFGame: Long): VideoGameDetailsResponse {
        return withContext(Dispatchers.IO) {
            val response = daoDB.getVideoGamesDetailsById(idVideoFGame)
            response
        }
    }
}
