package com.example.geo_quiz.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
import com.example.geo_quiz.ui.theme.TitleColor
import com.example.geo_quiz.ui.theme.FunColor

@Composable
fun QuizScreen(viewModel: QuizViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = BackgroundColor
    ) {
        when {
            uiState.currentTopic == null -> TopicSelectionScreen(
                onTopicSelected = { topic ->
                    viewModel.setTopic(topic)
                    viewModel.startQuiz()
                }
            )
            uiState.quizFinished -> ResultScreen(
                score = uiState.score,
                totalQuestions = viewModel.getTotalQuestions(),
                onRetry = { viewModel.retryQuiz() },
                onBackToTopics = { viewModel.backToTopicSelection() }
            )
            else -> {
                val question = viewModel.getCurrentQuestion()
                QuestionScreen(
                    question = question,
                    uiState = uiState,
                    onAnswerSelected = { viewModel.selectAnswer(it) },
                    onNextClicked = { viewModel.nextQuestion() },
                    questionNumber = uiState.currentQuestionIndex + 1,
                    totalQuestions = viewModel.getTotalQuestions()
                )
            }
        }
    }
}

@Composable
fun TopicSelectionScreen(onTopicSelected: (String) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "🧠 Choose Your Quiz! 🧠",
            style = MaterialTheme.typography.headlineLarge,
            color = TitleColor,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = { onTopicSelected("Geography") },
            colors = ButtonDefaults.buttonColors(containerColor = ButtonColor),
            modifier = Modifier.size(width = 250.dp, height = 70.dp)
        ) {
            Text(
                "🌍 Geography",
                color = ButtonTextColor,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { onTopicSelected("Astronomy") },
            colors = ButtonDefaults.buttonColors(containerColor = ButtonColor),
            modifier = Modifier.size(width = 250.dp, height = 70.dp)
        ) {
            Text(
                "🚀 Astronomy",
                color = ButtonTextColor,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun QuestionScreen(
    question: Question,
    uiState: com.example.geo_quiz.logic.QuizUiState,
    onAnswerSelected: (String) -> Unit,
    onNextClicked: () -> Unit,
    questionNumber: Int,
    totalQuestions: Int
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Progress indicator
        Text(
            text = "Question $questionNumber of $totalQuestions",
            style = MaterialTheme.typography.titleMedium,
            color = FunColor,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        LinearProgressIndicator(
            progress = questionNumber.toFloat() / totalQuestions,
            modifier = Modifier.fillMaxWidth(),
            color = FunColor,
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Text(
            text = question.text,
            style = MaterialTheme.typography.headlineSmall,
            color = QuestionTextColor,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
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
                    .padding(vertical = 4.dp)
                    .height(56.dp),
                enabled = uiState.selectedAnswer == null,
                colors = buttonColors
            ) {
                Text(
                    option, 
                    color = ButtonTextColor,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        if (uiState.selectedAnswer != null) {
            Spacer(modifier = Modifier.height(16.dp))
            if (uiState.isAnswerCorrect == true) {
                Text(
                    "🎉 Awesome! You got it right! 🎉", 
                    color = CorrectAnswer,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                question.funFact?.let {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        it, 
                        style = MaterialTheme.typography.bodyLarge,
                        color = QuestionTextColor,
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                Text(
                    "🤔 Oops! The correct answer is: ${question.correctAnswer}", 
                    color = WrongAnswer,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                question.funFact?.let {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        it, 
                        style = MaterialTheme.typography.bodyLarge,
                        color = QuestionTextColor,
                        textAlign = TextAlign.Center
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onNextClicked, 
                colors = ButtonDefaults.buttonColors(containerColor = FunColor),
                modifier = Modifier.size(width = 150.dp, height = 50.dp)
            ) {
                Text(
                    "Next! 🎯", 
                    color = ButtonTextColor,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun ResultScreen(score: Int, totalQuestions: Int, onRetry: () -> Unit, onBackToTopics: () -> Unit) {
    val percentage = (score.toFloat() / totalQuestions * 100).toInt()
    
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when {
            percentage >= 80 -> {
                Text(
                    "🏆 AMAZING! You're a Quiz Star! 🏆",
                    style = MaterialTheme.typography.headlineMedium,
                    color = CorrectAnswer,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
            percentage >= 60 -> {
                Text(
                    "🌟 Great Job! You know a lot! 🌟",
                    style = MaterialTheme.typography.headlineMedium,
                    color = FunColor,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
            else -> {
                Text(
                    "🎯 Good try! Let's learn more together! 🎯", 
                    style = MaterialTheme.typography.headlineMedium,
                    color = TitleColor,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "Your score: $score out of $totalQuestions",
            style = MaterialTheme.typography.titleLarge,
            color = QuestionTextColor,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = onRetry, 
            colors = ButtonDefaults.buttonColors(containerColor = ButtonColor),
            modifier = Modifier.size(width = 200.dp, height = 60.dp)
        ) {
            Text(
                "Retry Quiz 🔁", 
                color = ButtonTextColor,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onBackToTopics,
            colors = ButtonDefaults.buttonColors(containerColor = FunColor),
            modifier = Modifier.size(width = 200.dp, height = 60.dp)
        ) {
            Text(
                "Back to Topics 🧠",
                color = ButtonTextColor,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
} 