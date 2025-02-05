package com.ihm.anacofisk.model

data class Event (
    val title: String,
    val description: String,
    val number: Int,
    val choices: List<String>
)