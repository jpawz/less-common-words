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

    private Map<String, Map<String, WordRank>> database = new HashMap<>();

    public WordRank findByWord(String dbName, String word) {
	return database.get(dbName).get(word);
    }

    public List<WordRank> findByWordIn(String dbName, Set<String> words) {
	List<WordRank> wordRanks = new ArrayList<>();
	for (String word : words) {
	    if (database.get(dbName).containsKey(word)) {
		wordRanks.add(database.get(dbName).get(word));
	    }
	}
	return wordRanks;
    }

    public List<WordRank> findByWordInAndRankGreaterThan(String dbName, Set<String> words, int rank) {
	List<WordRank> wordRanks = new ArrayList<>();

	words.forEach(word -> {
	    WordRank wordRank = database.get(dbName).get(word);
	    if (wordRank != null && wordRank.getRank() > rank) {
		wordRanks.add(database.get(dbName).get(word));
	    }
	});

	return wordRanks;
    }

    public WordRank save(String dbName, WordRank wordRank) {
	database.putIfAbsent(dbName, new HashMap<>());
	database.get(dbName).put(wordRank.getWord(), wordRank);
	return database.get(dbName).get(wordRank.getWord());
    }

    public void saveAll(String dbName, List<WordRank> wordRanks) {
	database.putIfAbsent(dbName, new HashMap<>());
	wordRanks.forEach(w -> database.get(dbName).put(w.getWord(), w));

    }

    public long count(String dbName) {
	return database.get(dbName).size();
    }

    public Set<String> getDbNames() {
	return database.keySet();
    }
}
