package com.shane.jokegenerator;

import java.util.Random;

public class JokeGenerator {

    private String[] jokes = new String[] {
            "A user interface is like a joke. If you have to explain it, its not that good.",
            "There are only 10 kinds of people in this world: those who know binary and those who don't.",
            "How many programmers does it take to change a light bulb?\n" +
                    "None – It's a hardware problem"
    };

    private Random random;

    public JokeGenerator() {
        random = new Random();
    }

    public String getJoke() {
        return jokes[random.nextInt(jokes.length)];
    }
}
