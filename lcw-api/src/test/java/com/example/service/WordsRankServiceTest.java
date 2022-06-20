package com.example.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.domain.WordRank;
import com.example.repository.WordRankRepository;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WordsRankServiceTest {

	private WordsRankService service;

	@Mock
	WordRankRepository repository;

	@BeforeAll
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		this.service = new WordsRankService(repository);
	}

	@Test
	void whenNewWordIsSubmited_thenDefaultRankShouldBeReturned() {
		String knownWord = "the";
		String newWord = "undercarriage";
		Set<String> words = Set.of(knownWord, newWord);
		given(repository.findByWordIn(words)).willReturn(List.of(new WordRank(knownWord, 1)));

		List<WordRank> wordRanks = service.getWordsRankForWords(words);

		assertThat(wordRanks).contains(new WordRank(knownWord, 1)).hasSize(1);
	}

}
