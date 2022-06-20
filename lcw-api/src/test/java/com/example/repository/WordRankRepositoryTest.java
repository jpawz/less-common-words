package com.example.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import com.example.data.DataLoader;
import com.example.domain.WordRank;

@DataJpaTest
@Import(DataLoader.class)
class WordRankRepositoryTest {

    @Autowired
    private WordRankRepository repository;

    @Test
    void shoudProvideFilteredData() {
	String rank27 = "new";
	String rank41 = "search";
	String rank42 = "free";
	Set<String> words = new HashSet<>(Arrays.asList(rank27, rank41, rank42));

	List<WordRank> wordRanks = repository.findByWordInAndRankGreaterThan(words, 40);

	assertThat(wordRanks).containsExactlyInAnyOrder(repository.findByWord(rank41), repository.findByWord(rank42));
    }

}
