package com.example.elixirgameapp.presentation.ui.detailvg

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.elixirgameapp.data.local.database.AppDatabase
import com.example.elixirgameapp.data.network.api.VideoGameService
import com.example.elixirgameapp.data.network.retrofit.RetrofitHelper
import com.example.elixirgameapp.data.repository.VideoGameRepositoryImpl
import com.example.elixirgameapp.databinding.ActivityDetailBinding
import com.example.elixirgameapp.domain.VideoGameUseCase
import com.example.elixirgameapp.presentation.viewmodel.detailvg.DetailViewModel
import com.example.elixirgameapp.presentation.viewmodel.detailvg.ViewModelDetailsFactory
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {

    private lateinit var bindingDetail: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        bindingDetail = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(bindingDetail.root)


        val idVideoGame = intent.getLongExtra("ID_VIDEO_GAME", -1)
        if (idVideoGame == -1L) {
            finish()
        }

        val apiService = RetrofitHelper.getRetrofit().create(VideoGameService::class.java)
        val dataBase = AppDatabase.getDatabase(application)
        val repository = VideoGameRepositoryImpl(apiService, dataBase.videoGameDAO())
        val useCase = VideoGameUseCase(repository)
        val viewModelDetailFactory = ViewModelDetailsFactory(useCase)
        val viewModelDetail =
            ViewModelProvider(this, viewModelDetailFactory)[DetailViewModel::class.java]

        viewModelDetail.getDetailVideoGameById(idVideoGame)

        viewModelDetail.videoGameDetailLV.observe(this) {
            with(it) {
                bindingDetail.txtNameVideoGameDetail.text = it.name
                bindingDetail.ratingBar.rating = it.rating.toFloat()
                bindingDetail.releaseDateDetail.text = it.released
                bindingDetail.textGenre.text = it.genres
                bindingDetail.puntaje.text = it.metacritic.toString()
                bindingDetail.normalPrice.text = it.price.toString()
                bindingDetail.textLastPrice.text = it.lastPrice.toString()
                Picasso
                    .get()
                    .load(it.backgroundImage)
                    .into(bindingDetail.backgroundImageViewDetail)

                bindingDetail.sendEmail.setOnClickListener {
                    sendEmailWithVideoGame(name, id)

                }
            }

        }
    }

    private fun sendEmailWithVideoGame(nameVideoGame: String, idVideoGame: Long) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "message/rfc822"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("glira27@gmail.com"))
        intent.putExtra(Intent.EXTRA_SUBJECT, "Quiero un Video Juego")
        intent.putExtra(
            Intent.EXTRA_TEXT, "Hola \n" +
                    " Vi el juego $nameVideoGame con ID $idVideoGame y me gustaría " +
                    "que me contactaran a este correo o al siguiente número"
        )
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(Intent.createChooser(intent, "Enviar por correo Video Juego"))
        } else
            Toast.makeText(
                this,
                "Tienes que tener instalada una aplicación de correo",
                Toast.LENGTH_LONG
            ).show()


    }
}



