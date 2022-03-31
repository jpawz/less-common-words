package com.example.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
	public void shouldAssignLowestRankToIgnoredWords() {

	}

}
