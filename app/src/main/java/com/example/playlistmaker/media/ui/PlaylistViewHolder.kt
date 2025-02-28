package com.example.playlistmaker.media.ui

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.playlistmaker.R
import com.example.playlistmaker.databinding.PlaylistViewBinding
import com.example.playlistmaker.media.domain.model.Playlist
import com.example.playlistmaker.search.domain.models.TrackWord

class PlaylistViewHolder(private val binding: PlaylistViewBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(playlist: Playlist) {
        with(binding) {
            playlistName.text = playlist.playlistName
            tracksCount.text = playlist.tracksCount.toString()+" "+ TrackWord.getTrackWord(playlist.tracksCount, itemView)
        }
        Glide.with(itemView).load(playlist.uri).centerCrop()
            .placeholder(R.drawable.placeholder).into(binding.playlistCover)
    }
}