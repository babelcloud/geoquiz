# GeoQuiz

A simple quiz game to test your knowledge of U.S. geography.

## Features

- Main screen with a "Start Quiz" button.
- Quiz screen presents one question at a time with 4 multiple-choice options.
- Questions cover topics like: U.S. states, capitals, major rivers, mountains, and landmarks.
- User selects an answer and immediately sees if it’s correct or not.
- At the end of 10 questions, a results screen is shown with the final score and a button to retry.
- A "Fun Fact" about the correct answer is shown after each question is answered.

## How to Run

1.  Open the project in Android Studio.
2.  Let Android Studio download the necessary Gradle dependencies.
3.  Run the app on an Android emulator or a physical device.

## Project Structure

-   `app/src/main/java/com/example/geo_quiz/data`: Contains the data models and the quiz questions.
-   `app/src/main/java/com/example/geo_quiz/logic`: Contains the `QuizViewModel` which holds the business logic of the app.
-   `app/src/main/java/com/example/geo_quiz/ui`: Contains the Jetpack Compose UI components for the app.
-   `app/src/main/java/com/example/geo_quiz/ui/theme`: Contains the theme and color definitions.
-   `app/src/main/java/com/example/geo_quiz/MainActivity.kt`: The main entry point of the app. 