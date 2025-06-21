package com.example.geo_quiz.logic

import androidx.lifecycle.ViewModel
import com.example.geo_quiz.data.QuizData
import com.example.geo_quiz.data.Question
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class QuizUiState(
    val currentQuestionIndex: Int = 0,
    val score: Int = 0,
    val selectedAnswer: String? = null,
    val isAnswerCorrect: Boolean? = null,
    val quizFinished: Boolean = false,
    val quizStarted: Boolean = false
)

class QuizViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(QuizUiState())
    val uiState: StateFlow<QuizUiState> = _uiState.asStateFlow()

    private var questions: List<Question> = QuizData.questions.shuffled()

    fun startQuiz() {
        questions = QuizData.questions.shuffled()
        _uiState.update { it.copy(quizStarted = true, quizFinished = false, score = 0, currentQuestionIndex = 0, selectedAnswer = null, isAnswerCorrect = null) }
    }

    fun getCurrentQuestion(): Question {
        return questions[_uiState.value.currentQuestionIndex]
    }

    fun selectAnswer(answer: String) {
        val correctAnswer = getCurrentQuestion().correctAnswer
        val isCorrect = answer == correctAnswer
        _uiState.update { currentState ->
            currentState.copy(
                selectedAnswer = answer,
                isAnswerCorrect = isCorrect,
                score = if (isCorrect) currentState.score + 1 else currentState.score
            )
        }
    }

    fun nextQuestion() {
        val nextQuestionIndex = _uiState.value.currentQuestionIndex + 1
        if (nextQuestionIndex < questions.size) {
            _uiState.update { currentState ->
                currentState.copy(
                    currentQuestionIndex = nextQuestionIndex,
                    selectedAnswer = null,
                    isAnswerCorrect = null
                )
            }
        } else {
            _uiState.update { it.copy(quizFinished = true) }
        }
    }

    fun retryQuiz() {
        startQuiz()
    }
} 