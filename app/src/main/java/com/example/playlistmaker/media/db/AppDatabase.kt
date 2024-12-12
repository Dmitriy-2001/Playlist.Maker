package com.example.playlistmaker.media.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TrackEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteTracksDao(): FavoriteTracksDao
}