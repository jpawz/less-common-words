package com.example.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.domain.WordRank;
import com.example.repository.WordRankRepository;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WordsRankServiceImplTest {

	private WordsRankService service;

	@Mock
	WordRankRepository repository;

	@BeforeAll
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		this.service = new WordsRankServiceImpl(repository);
	}

	@Test
	public void shouldSplitTextIntoWords() {
		String text = "Spring makes programming Java quicker.";

		List<String> wordsFromText = WordsRankServiceImpl.getWords(text);

		assertThat(wordsFromText).contains("Spring", "makes", "programming", "Java", "quicker");
	}

	@Test
	public void whenNewWordIsSubmited_thenDefaultRankShouldBeReturned() {
		String newWord = "java";
		String knownWord = "programming";
		String text = newWord + " " + knownWord;
		given(repository.findByWordIn(List.of(newWord, knownWord))).willReturn(List.of(new WordRank(knownWord, 1)));

		List<WordRank> wordRanks = service.getWordsRankForText(text);

		assertThat(wordRanks).contains(new WordRank(knownWord, 1), new WordRank(newWord, WordRank.RANK_NEW));
	}

}
