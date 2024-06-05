package com.example.elixirgameapp.presentation.ui.listvg

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.elixirgameapp.data.response.VideoGameResponse
import com.example.elixirgameapp.databinding.GameItemBinding
import com.squareup.picasso.Picasso

class VideoGameAdapter : RecyclerView.Adapter<VideoGameAdapter.ViewHolder>() {

    lateinit var onItemClickListener: (VideoGameResponse) -> Unit

    var videoGames = mutableListOf<VideoGameResponse>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = GameItemBinding.inflate(
            LayoutInflater.from(parent.context), parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val videoGame = videoGames[position]
        holder.bindVideoGame(videoGame)
    }

    override fun getItemCount(): Int {
        return videoGames.size
    }

    inner class ViewHolder(private var binding: GameItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindVideoGame(videoGame: VideoGameResponse) {
            binding.image1.setImageResource(0)
            Picasso.get()
                .load(videoGame.backgroundImage)
                .resize(200, 200)
                .centerCrop()
                .into(binding.image1)

            binding.txtNameVideoGame.text = videoGame.name
            binding.txtReleaseVideoGame.text = videoGame.released
            binding.txtRatingVideoGame.text = videoGame.rating.toString()
            clickVideoGameListener(videoGame)

        }

        private fun clickVideoGameListener(videoGame: VideoGameResponse) {
            binding.root.setOnClickListener {
                if (::onItemClickListener.isInitialized)
                    onItemClickListener(videoGame)
                else
                    Log.e("VideoGameAdapter", "Listener no inicializada")
            }
        }
    }
}