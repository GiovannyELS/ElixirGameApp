package com.example.elixirgameapp.presentation.viewmodel.listvg

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.elixirgameapp.data.response.VideoGameResponse
import com.example.elixirgameapp.domain.VideoGameUseCase
import kotlinx.coroutines.launch

class VideoGameViewModel(private val useCase: VideoGameUseCase) : ViewModel() {

    private var videoGameList = MutableLiveData<List<VideoGameResponse>>()

    val videoGameLV
        get() = videoGameList


    fun getAllVideoGamesFromServer() {
        viewModelScope.launch {
            try {

                val response = useCase.getAllVideoGamesOnStock()
                if (response.isNotEmpty()) {
                    useCase.saveAllVideoGameDB(response)
                    response.forEach { videGame ->
                        val detailResponse = useCase.getVideoGameByIdOnStock(videGame.id)
                        useCase.saveDetailVideoGameDB(detailResponse)
                    }
                }
                videoGameList.value = response

            } catch (e: Exception) {
                Log.e("MainActivity","Not network connection")
                videoGameList.value = useCase.getAllVideoGameDB()
            }
        }
    }
}
