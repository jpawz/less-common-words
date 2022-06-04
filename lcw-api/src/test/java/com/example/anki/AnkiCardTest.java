package com.example.anki;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class AnkiCardTest {
	private final HashMap<String, String> question = new HashMap<>();
	private final HashMap<String, String> answer = new HashMap<>();
	private final String frontValue = "value for front";
	private final String backValue = "value for back";
	private AnkiCard ankiCard;

	@BeforeEach
	public void setUp() {
		question.put("front", frontValue);
		answer.put("back", backValue);
		ankiCard = new AnkiCard(question, answer);
	}

	@Test
	void checkValueForQuestion() {
		assertThat(ankiCard.question()).isEqualTo(question);
	}

	@Test
	void checkValueForAnswer() {
		assertThat(ankiCard.answer()).isEqualTo(answer);
	}

}
