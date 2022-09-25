package com.example.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.domain.WordRank;

class WordRankRepositoryTest {

    private WordRankRepository repository;

    @BeforeEach
    void setUp() {
	this.repository = new WordRankRepository();
    }

    @Test
    void shouldSaveWordRanks() {
	WordRank wordRank1 = new WordRank("word", 1);
	WordRank wordRank2 = new WordRank("another", 5);
	long initialCount = repository.count(repository.getDbNames().get(0));

	repository.save(repository.getDbNames().get(0), wordRank1);
	repository.save(repository.getDbNames().get(0), wordRank2);

	assertThat(repository.count(repository.getDbNames().get(0))).isEqualTo(initialCount + 2);
    }

    @Test
    void shouldSaveAllWordRanks() {
	List<WordRank> wordRanks = Arrays.asList(new WordRank("some", 1), new WordRank("word", 2));

	repository.saveAll(repository.getDbNames().get(0), wordRanks);

	assertThat(repository.count(repository.getDbNames().get(0))).isEqualTo(2);
    }

    @Test
    void shouldFindWordRankByWord() {
	WordRank wordRankToSave = new WordRank("word", 1);
	repository.save(repository.getDbNames().get(0), wordRankToSave);

	WordRank fetchedWordRank = repository.findByWord(repository.getDbNames().get(0), "word");

	assertThat(fetchedWordRank).isEqualTo(wordRankToSave);
    }

    @Test
    void shoudProvideFilteredData() {
	String rank27 = "new";
	String rank41 = "search";
	String rank42 = "free";
	int rankLimit = 40;
	Set<String> words = new HashSet<>(Arrays.asList(rank27, rank41, rank42));
	repository.saveAll(repository.getDbNames().get(0),
		Arrays.asList(new WordRank(rank27, 27), new WordRank(rank41, 41), new WordRank(rank42, 42)));

	List<WordRank> wordRanks = repository.findByWordInAndRankGreaterThan(repository.getDbNames().get(0), words,
		rankLimit);

	assertThat(wordRanks).containsExactlyInAnyOrder(repository.findByWord(repository.getDbNames().get(0), rank41),
		repository.findByWord(repository.getDbNames().get(0), rank42));
    }

}
