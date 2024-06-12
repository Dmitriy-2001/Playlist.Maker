package com.example.playlistmaker.search.data.dto

import android.content.SharedPreferences
import com.example.playlistmaker.search.domain.models.Track
import com.example.playlistmaker.search.ui.KEY_FOR_HISTORY_LIST
import com.google.gson.Gson

class SearchHistory(val sharedPreferences: SharedPreferences) {
    val historyList: ArrayList<Track> = ArrayList(readFromSH().toList())
    fun addTrack(track: Track) {
        val index = historyList.indexOfFirst { it.trackId == track.trackId }

        if (historyList.size < 10) {
            if (index == -1) {
                historyList.add(0, track)
            } else {
                shiftElementToTopOfHistoryList(index)
            }
        } else {
            if (index == -1) {
                historyList.removeAt(historyList.lastIndex)
                historyList.add(0, track)
            } else {
                shiftElementToTopOfHistoryList(index)
            }
        }
    }
    fun clearHistoryList() {
        historyList.clear()
    }
    fun saveToSH(historyList: ArrayList<Track>) {
        val json = Gson().toJson(historyList)
        sharedPreferences.edit()
            .putString(KEY_FOR_HISTORY_LIST, json)
            .apply()
    }
    private fun readFromSH(): Array<Track> {
        val json = sharedPreferences.getString(KEY_FOR_HISTORY_LIST, null) ?: return emptyArray()
        return Gson().fromJson(json, Array<Track>::class.java)
    }

    private fun shiftElementToTopOfHistoryList(index: Int) {
        val trackToMove = historyList[index]
        historyList.removeAt(index)
        historyList.add(0, trackToMove)
    }
}