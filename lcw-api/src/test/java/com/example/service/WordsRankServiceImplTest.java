package com.example.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
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
	public void shouldAssignProperRankForUnknownWords() {
		String text = "text with unknown word";
		List<WordRank> baseRank = new ArrayList<WordRank>(
				List.of(new WordRank("text", 1), new WordRank("with", 2), new WordRank("word", 3)));
		given(repository.findByWordIn(List.of("text", "with", "unknown", "word")))
				.willReturn(baseRank);

		List<WordRank> newRank = service.getWordsRankForText(text);

		assertThat(newRank).contains(new WordRank("unknown", WordRank.RANK_NEVER_IGNORE));
	}

	@Test
	public void shouldAssignLowestRankToIgnoredWords() {

	}

}
