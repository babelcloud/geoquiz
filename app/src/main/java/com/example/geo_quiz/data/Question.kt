package com.example.geo_quiz.data

data class Question(
    val text: String,
    val options: List<String>,
    val correctAnswer: String,
    val funFact: String? = null
) 