package com.example.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.example.domain.WordRank;

class WordsRankServiceImplTest {

	@Test
	public void shouldAssignProperRankForUnknownWords() {
		String text = "text with unknown word";
		List<WordRank> baseRank = List.of(new WordRank("text", 1), new WordRank("with", 2),
				new WordRank("word", 3));
		WordsRankService service = new WordsRankServiceImpl();
		service.setWordsRank(baseRank);

		List<WordRank> newRank = service.getWordsRankForText(text);

		assertThat(newRank).contains(new WordRank("unknown", WordRank.RANK_NEVER_IGNORE));
	}

	@Test
	public void shouldAssignLowestRankToIgnoredWords() {

	}

}
