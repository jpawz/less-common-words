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

	repository.save(wordRank1);
	repository.save(wordRank2);

	assertThat(repository.count()).isEqualTo(2);
    }

    @Test
    void shouldSaveAllWordRanks() {
	List<WordRank> wordRanks = Arrays.asList(new WordRank("some", 1), new WordRank("word", 2));

	repository.saveAll(wordRanks);

	assertThat(repository.count()).isEqualTo(2);
    }

    @Test
    void shouldFindWordRankByWord() {
	WordRank wordRank1 = new WordRank("word", 1);

	repository.save(wordRank1);

	assertThat(repository.findByWord("word")).isEqualTo(wordRank1);
    }

    @Test
    void shoudProvideFilteredData() {
	String rank27 = "new";
	String rank41 = "search";
	String rank42 = "free";
	int rankLimit = 40;
	Set<String> words = new HashSet<>(Arrays.asList(rank27, rank41, rank42));

	List<WordRank> wordRanks = repository.findByWordInAndRankGreaterThan(words, rankLimit);

	assertThat(wordRanks).containsExactlyInAnyOrder(repository.findByWord(rank41), repository.findByWord(rank42));
    }

}
