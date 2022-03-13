package com.example.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class WordsRankServiceImplTest {

	private String sampleText = "text with less common word: patrichor.";
	private static Map<String, Integer> wordsRank = new HashMap<>();

	@BeforeAll
	public static void setup() {
		wordsRank = Map.of("the", 1, "one", 2, "with", 3, "less", 4, "common", 5, "word", 6,
				"petrichor", 99);
	}

	@Test
	public void shouldReturnWordWithinRank() {
		WordsRankService rankService = new WordsRankServiceImpl();
		rankService.setWordsRank(wordsRank);
		int rankLimit = 50;

		Map<String, Integer> sampleWordsRank = rankService.getWordsRank(sampleText, rankLimit);

		assertThat(sampleWordsRank).containsKeys("the", "one", "common")
				.doesNotContainKey("patrichor");
	}
	
	

}
