package com.example.elixirgameapp.domain

import com.example.elixirgameapp.data.repository.VideoGameRepositoryImpl
import com.example.elixirgameapp.data.response.VideoGameDetailsResponse
import com.example.elixirgameapp.data.response.VideoGameResponse

class VideoGameUseCase(private val repository: VideoGameRepositoryImpl) {

    suspend fun getAllVideoGamesOnStock(): MutableList<VideoGameResponse> {
        return repository.fetchVideoGames()
    }

    suspend fun getVideoGameByIdOnStock(idVideogame: Long): VideoGameDetailsResponse {
        return repository.fetchVideoGameById(idVideogame)
    }

    /**
     * Data Base
     */

    suspend fun saveAllVideoGameDB(videoGames: MutableList<VideoGameResponse>) {
        return repository.saveAllVideoGameOnDB(videoGames)
    }

    suspend fun getAllVideoGameDB(): MutableList<VideoGameResponse>{
        return repository.getAllVideoGameFromDB()
    }

    suspend fun saveDetailVideoGameDB(videoGameDetail: VideoGameDetailsResponse){
        return repository.saveDetailAllVideoGameOnDB(videoGameDetail)
    }

    suspend fun getDetailVideoGameDB(idVideogame: Long): VideoGameDetailsResponse{
        return repository.getDetailVideoGameFromDB(idVideogame)
    }

}

