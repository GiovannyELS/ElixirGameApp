package com.example.elixirgameapp.presentation.viewmodel.detailvg

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.elixirgameapp.data.response.VideoGameDetailsResponse
import com.example.elixirgameapp.domain.VideoGameUseCase
import kotlinx.coroutines.launch

class DetailViewModel(private val useCase: VideoGameUseCase): ViewModel(){

    private val _videoGameDetail = MutableLiveData<VideoGameDetailsResponse>()
        val videoGameDetailLV : MutableLiveData<VideoGameDetailsResponse>
            get() = _videoGameDetail


    fun getDetailVideoGameById(idvideoGame: Long){
        viewModelScope.launch {

            try{
                val videoGame = useCase.getDetailVideoGameDB(idvideoGame)
                _videoGameDetail.value = videoGame

            } catch (e: Exception){
                Log.e("DetailActivity", "Not network connection")
                _videoGameDetail.value = useCase.getDetailVideoGameDB(idvideoGame)
            }
        }
    }
}