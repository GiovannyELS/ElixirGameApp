package com.example.elixirgameapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.elixirgameapp.data.response.VideoGameDetailsResponse
import com.example.elixirgameapp.data.response.VideoGameResponse


@Dao

interface VideoGameDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideoGames(videoGameResponse: MutableList<VideoGameResponse>)

    @Query("SELECT * FROM video_games")
    suspend fun getAllVideoGames(): MutableList<VideoGameResponse>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideoGamesDetails(videoGameDetails: VideoGameDetailsResponse)

    @Query( "SELECT * FROM video_games_details WHERE id = :idVideoGame")
    suspend fun getVideoGamesDetailsById(idVideoGame: Long): VideoGameDetailsResponse


}