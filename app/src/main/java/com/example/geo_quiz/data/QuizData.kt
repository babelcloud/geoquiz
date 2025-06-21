package com.example.geo_quiz.data

object QuizData {
    val questions = listOf(
        Question(
            text = "What is the capital of California?",
            options = listOf("Los Angeles", "Sacramento", "San Diego", "San Francisco"),
            correctAnswer = "Sacramento",
            funFact = "Sacramento is known as the 'City of Trees' because of its abundant urban forest."
        ),
        Question(
            text = "Which state is known as the 'Sunshine State'?",
            options = listOf("Arizona", "Florida", "California", "Hawaii"),
            correctAnswer = "Florida",
            funFact = "Florida is the southernmost contiguous state in the United States."
        ),
        Question(
            text = "What is the longest river in the United States?",
            options = listOf("Mississippi River", "Missouri River", "Yukon River", "Rio Grande"),
            correctAnswer = "Missouri River",
            funFact = "The Missouri River is often called the 'Big Muddy' because of the large amount of sediment it carries."
        ),
        Question(
            text = "Mount Rushmore is located in which state?",
            options = listOf("Wyoming", "Montana", "North Dakota", "South Dakota"),
            correctAnswer = "South Dakota",
            funFact = "The four presidents carved into Mount Rushmore are Abraham Lincoln, Theodore Roosevelt, George Washington, and Thomas Jefferson."
        ),
        Question(
            text = "Which of the Great Lakes is the largest by surface area?",
            options = listOf("Lake Huron", "Lake Michigan", "Lake Superior", "Lake Erie"),
            correctAnswer = "Lake Superior",
            funFact = "Lake Superior contains 10% of the world's surface fresh water."
        )
    )
} 