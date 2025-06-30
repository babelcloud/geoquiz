package com.example.geo_quiz.data

object QuizData {
    val geographyQuestions = listOf(
        Question(
            text = "🌎 What color is most of our Earth covered with?",
            options = listOf("Green", "Blue", "Brown", "Yellow"),
            correctAnswer = "Blue",
            funFact = "🌊 The Earth looks blue from space because most of it is covered with water! That's why we call it the 'Blue Planet'!"
        ),
        Question(
            text = "🐧 Which cold place is home to penguins?",
            options = listOf("North Pole", "Antarctica", "Canada", "Alaska"),
            correctAnswer = "Antarctica",
            funFact = "🐧 Antarctica is the coldest continent! It's so cold that penguins have special feathers to keep warm!"
        ),
        Question(
            text = "🦘 Which country is famous for kangaroos?",
            options = listOf("Brazil", "India", "Australia", "Mexico"),
            correctAnswer = "Australia",
            funFact = "🦘 Australia is home to kangaroos, koalas, and many other unique animals you can't find anywhere else!"
        ),
        Question(
            text = "🗺️ What are the 7 big areas of land on Earth called?",
            options = listOf("Countries", "Continents", "Islands", "Cities"),
            correctAnswer = "Continents",
            funFact = "🌍 The 7 continents are: Asia, Africa, North America, South America, Antarctica, Europe, and Australia!"
        ),
        Question(
            text = "🏔️ What do we call a very tall mountain that can shoot out hot lava?",
            options = listOf("Hill", "Volcano", "Cave", "Valley"),
            correctAnswer = "Volcano",
            funFact = "🌋 Volcanoes are like Earth's way of letting out steam! The hot melted rock inside is called magma!"
        ),
        Question(
            text = "🏜️ What is a very dry place with lots of sand called?",
            options = listOf("Forest", "Ocean", "Desert", "Lake"),
            correctAnswer = "Desert",
            funFact = "🐪 Deserts are very dry places where camels live! Some deserts are hot during the day but cold at night!"
        ),
        Question(
            text = "🌳 What do we call a very large area filled with trees?",
            options = listOf("Desert", "Ocean", "Forest", "Mountain"),
            correctAnswer = "Forest",
            funFact = "🦉 Forests are home to many animals like bears, deer, owls, and squirrels! Trees also help us breathe clean air!"
        ),
        Question(
            text = "🌊 What is the largest ocean on Earth?",
            options = listOf("Atlantic Ocean", "Pacific Ocean", "Indian Ocean", "Arctic Ocean"),
            correctAnswer = "Pacific Ocean",
            funFact = "🐋 The Pacific Ocean is so big that all the continents could fit inside it! It's home to whales, dolphins, and colorful fish!"
        )
    )

    val astronomyQuestions = listOf(
        Question(
            text = "☀️ What is the name of the star closest to Earth?",
            options = listOf("Moon", "Sun", "Mars", "Venus"),
            correctAnswer = "Sun",
            funFact = "☀️ The Sun is a giant star that gives us light and heat! It's so big that one million Earths could fit inside it!"
        ),
        Question(
            text = "🪐 Which planet is known for its beautiful rings?",
            options = listOf("Earth", "Mars", "Jupiter", "Saturn"),
            correctAnswer = "Saturn",
            funFact = "🪐 Saturn's rings are made of ice, dust, and rock! Some pieces are as small as a grain of sand, and others are as big as a house!"
        ),
        Question(
            text = "🧑‍🚀 What do we call a person who travels to space?",
            options = listOf("Pilot", "Sailor", "Astronaut", "Diver"),
            correctAnswer = "Astronaut",
            funFact = "🧑‍🚀 Astronauts wear special suits to protect them in space! They float around because there's no gravity!"
        ),
        Question(
            text = "🌕 What is the name of Earth's natural satellite?",
            options = listOf("Sun", "Moon", "Mars", "Venus"),
            correctAnswer = "Moon",
            funFact = "🌕 The Moon orbits the Earth and it's what we see in the night sky! Its shape seems to change, but it's just how the sun lights it up!"
        ),
        Question(
            text = "💫 What is a shooting star?",
            options = listOf("A real star falling", "A piece of rock from space", "An airplane", "A satellite"),
            correctAnswer = "A piece of rock from space",
            funFact = "🌠 A shooting star is not a star at all! It's a small piece of rock or dust from space called a meteoroid that burns up when it enters Earth's atmosphere."
        ),
        Question(
            text = "🌌 What is the name of our galaxy?",
            options = listOf("Andromeda", "Triangulum", "Milky Way", "Whirlpool"),
            correctAnswer = "Milky Way",
            funFact = "🌌 Our solar system is part of a huge galaxy called the Milky Way! It has billions of stars, and on a clear night, you can see its faint, milky band of light."
        ),
        Question(
            text = "🔴 Which planet is known as the 'Red Planet'?",
            options = listOf("Mars", "Venus", "Jupiter", "Mercury"),
            correctAnswer = "Mars",
            funFact = "🔴 Mars is called the Red Planet because it's covered in reddish dust! Scientists have sent rovers to explore Mars and look for signs of life."
        ),
        Question(
            text = "🔭 What instrument do scientists use to see faraway stars and planets?",
            options = listOf("Microscope", "Binoculars", "Telescope", "Magnifying glass"),
            correctAnswer = "Telescope",
            funFact = "🔭 Telescopes help us see things that are very far away in space! The Hubble Space Telescope has taken amazing pictures of distant galaxies."
        )
    )

    fun getQuestions(topic: String): List<Question> {
        return when (topic) {
            "Geography" -> geographyQuestions
            "Astronomy" -> astronomyQuestions
            else -> geographyQuestions // Default to Geography
        }
    }
} 