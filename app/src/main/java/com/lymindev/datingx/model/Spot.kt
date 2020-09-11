package com.lymindev.datingx.model

data class Spot(
        val name: String,
        val city: String,
        val url: String
) {
    companion object {
        private var counter = 0L
    }
}