package com.example.elixirgameapp.presentation.viewmodel.detailvg

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.elixirgameapp.domain.VideoGameUseCase

class ViewModelDetailsFactory (private val videoGameUseCase: VideoGameUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel (videoGameUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}