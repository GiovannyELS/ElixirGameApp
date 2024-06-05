package com.example.elixirgameapp.presentation.ui.listvg

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.elixirgameapp.data.local.database.AppDatabase
import com.example.elixirgameapp.data.network.api.VideoGameService
import com.example.elixirgameapp.data.network.retrofit.RetrofitHelper
import com.example.elixirgameapp.data.repository.VideoGameRepositoryImpl
import com.example.elixirgameapp.databinding.ActivityMainBinding
import com.example.elixirgameapp.domain.VideoGameUseCase
import com.example.elixirgameapp.presentation.ui.detailvg.DetailActivity
import com.example.elixirgameapp.presentation.viewmodel.listvg.VideoGameViewModel
import com.example.elixirgameapp.presentation.viewmodel.listvg.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val apiService = RetrofitHelper.getRetrofit().create(VideoGameService::class.java)
        val dataBase = AppDatabase.getDatabase(application)
        val repository = VideoGameRepositoryImpl(apiService, dataBase.videoGameDAO())
        val useCase = VideoGameUseCase(repository)
        val viewModelFactory = ViewModelFactory(useCase)
        val viewModel = ViewModelProvider(this, viewModelFactory)[VideoGameViewModel::class.java]

        viewModel.getAllVideoGamesFromServer()


        val adapterVideoGame = VideoGameAdapter()
        binding.vgRecycler.adapter = adapterVideoGame
        binding.vgRecycler.layoutManager = LinearLayoutManager(this)


        viewModel.videoGameLV.observe(this) {
            Log.i("GAMES", "games")
            adapterVideoGame.videoGames = it.toMutableList()
        }

        adapterVideoGame.onItemClickListener = {
            val idVideoGame = it.id
            val nombreVideoGame = it.name

            goToVideoGameDetailPage(idVideoGame)
        }
    }

    private fun goToVideoGameDetailPage(idVideoGame: Long) {
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra("ID_VIDEO_GAME", idVideoGame)
        }
        startActivity(intent)
    }
}