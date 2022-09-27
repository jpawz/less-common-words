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
    private String dbName = "the_database";

    @BeforeEach
    void setUp() {
	this.repository = new WordRankRepository();
    }

    @Test
    void shouldSaveWordRanks() {
	WordRank wordRank1 = new WordRank("word", 1);
	WordRank wordRank2 = new WordRank("another", 5);

	repository.save(dbName, wordRank1);
	repository.save(dbName, wordRank2);

	assertThat(repository.count(dbName)).isEqualTo(2);
    }

    @Test
    void shouldSaveAllWordRanks() {
	List<WordRank> wordRanks = Arrays.asList(new WordRank("some", 1), new WordRank("word", 2));

	repository.saveAll(dbName, wordRanks);

	assertThat(repository.count(dbName)).isEqualTo(2);
    }

    @Test
    void shouldFindWordRankByWord() {
	WordRank wordRankToSave = new WordRank("word", 1);
	repository.save(dbName, wordRankToSave);

	WordRank fetchedWordRank = repository.findByWord(dbName, "word");

	assertThat(fetchedWordRank).isEqualTo(wordRankToSave);
    }

    @Test
    void shoudProvideFilteredData() {
	String rank27 = "new";
	String rank41 = "search";
	String rank42 = "free";
	int rankLimit = 40;
	Set<String> words = new HashSet<>(Arrays.asList(rank27, rank41, rank42));
	repository.saveAll(dbName,
		Arrays.asList(new WordRank(rank27, 27), new WordRank(rank41, 41), new WordRank(rank42, 42)));

	List<WordRank> wordRanks = repository.findByWordInAndRankGreaterThan(dbName, words,
		rankLimit);

	assertThat(wordRanks).containsExactlyInAnyOrder(repository.findByWord(dbName, rank41),
		repository.findByWord(dbName, rank42));
    }

}
