package com.example.geo_quiz.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.geo_quiz.logic.QuizViewModel
import com.example.geo_quiz.data.Question
import com.example.geo_quiz.ui.theme.CorrectAnswer
import com.example.geo_quiz.ui.theme.WrongAnswer
import com.example.geo_quiz.ui.theme.BackgroundColor
import com.example.geo_quiz.ui.theme.QuestionTextColor
import com.example.geo_quiz.ui.theme.ButtonColor
import com.example.geo_quiz.ui.theme.ButtonTextColor

@Composable
fun QuizScreen(viewModel: QuizViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = BackgroundColor
    ) {
        when {
            !uiState.quizStarted -> StartScreen(onStartQuiz = { viewModel.startQuiz() })
            uiState.quizFinished -> ResultScreen(
                score = uiState.score,
                onRetry = { viewModel.retryQuiz() }
            )
            else -> {
                val question = viewModel.getCurrentQuestion()
                QuestionScreen(
                    question = question,
                    uiState = uiState,
                    onAnswerSelected = { viewModel.selectAnswer(it) },
                    onNextClicked = { viewModel.nextQuestion() }
                )
            }
        }
    }
}

@Composable
fun StartScreen(onStartQuiz: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("U.S. Geography Quiz", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = onStartQuiz, colors = ButtonDefaults.buttonColors(containerColor = ButtonColor)) {
            Text("Start Quiz", color = ButtonTextColor)
        }
    }
}

@Composable
fun QuestionScreen(
    question: Question,
    uiState: com.example.geo_quiz.logic.QuizUiState,
    onAnswerSelected: (String) -> Unit,
    onNextClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = question.text,
            style = MaterialTheme.typography.headlineSmall,
            color = QuestionTextColor
        )
        Spacer(modifier = Modifier.height(24.dp))
        question.options.forEach { option ->
            val isSelected = uiState.selectedAnswer == option
            val buttonColors = when {
                !isSelected -> ButtonDefaults.buttonColors(containerColor = ButtonColor)
                uiState.isAnswerCorrect == true -> ButtonDefaults.buttonColors(containerColor = CorrectAnswer)
                else -> ButtonDefaults.buttonColors(containerColor = WrongAnswer)
            }
            Button(
                onClick = { onAnswerSelected(option) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                enabled = uiState.selectedAnswer == null,
                colors = buttonColors
            ) {
                Text(option, color = ButtonTextColor)
            }
        }

        if (uiState.selectedAnswer != null) {
            Spacer(modifier = Modifier.height(16.dp))
            if (uiState.isAnswerCorrect == true) {
                Text("Correct!", color = CorrectAnswer)
                question.funFact?.let {
                    Text(it, style = MaterialTheme.typography.bodyMedium)
                }
            } else {
                Text("Wrong! The correct answer is ${question.correctAnswer}", color = WrongAnswer)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onNextClicked, colors = ButtonDefaults.buttonColors(containerColor = ButtonColor)) {
                Text("Next", color = ButtonTextColor)
            }
        }
    }
}

@Composable
fun ResultScreen(score: Int, onRetry: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Quiz Finished!", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Your score: $score / ${com.example.geo_quiz.data.QuizData.questions.size}", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = onRetry, colors = ButtonDefaults.buttonColors(containerColor = ButtonColor)) {
            Text("Retry Quiz", color = ButtonTextColor)
        }
    }
} 