package com.example.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.example.domain.WordRank;

@Repository
public class WordRankRepository {

    private Map<String, WordRank> database = new HashMap<>();

    public WordRank findByWord(String word) {
	return database.get(word);
    }

    public List<WordRank> findByWordIn(Set<String> words) {
	List<WordRank> wordRanks = new ArrayList<>();
	for (String word : words) {
	    if (database.containsKey(word)) {
		wordRanks.add(database.get(word));
	    }
	}
	return wordRanks;
    }

    public List<WordRank> findByWordInAndRankGreaterThan(Set<String> words, int rank) {
	List<WordRank> wordRanks = new ArrayList<>();

	words.forEach(word -> {
	    WordRank wordRank = database.get(word);
	    if (wordRank != null && wordRank.getRank() > rank) {
		wordRanks.add(database.get(word));
	    }
	});

	return wordRanks;
    }

    public WordRank save(WordRank wordRank) {
	return database.put(wordRank.getWord(), wordRank);
    }

    public void saveAll(List<WordRank> wordRanks) {
	wordRanks.forEach(w -> database.put(w.getWord(), w));

    }

    public long count() {
	return database.size();
    }
}
