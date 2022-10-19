package com.example.instagramclone

object Constants {

    fun getUrl(id: Int): String {
        return "https://picsum.photos/id/${id + 10}/500/500"
    }
}
