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

    public WordRank findByWord(String dataset, String word) {
	return database.get(dataset).get(word);
    }

    public List<WordRank> findByWordIn(String dataset, Set<String> words) {
	List<WordRank> wordRanks = new ArrayList<>();
	for (String word : words) {
	    if (database.get(dataset).containsKey(word)) {
		wordRanks.add(database.get(dataset).get(word));
	    }
	}
	return wordRanks;
    }

    public List<WordRank> findByWordInAndRankGreaterThan(String dataset, Set<String> words, int rank) {
	List<WordRank> wordRanks = new ArrayList<>();

	words.forEach(word -> {
	    WordRank wordRank = database.get(dataset).get(word);
	    if (wordRank != null && wordRank.getRank() > rank) {
		wordRanks.add(database.get(dataset).get(word));
	    }
	});

	return wordRanks;
    }

    public WordRank save(String dataset, WordRank wordRank) {
	database.putIfAbsent(dataset, new HashMap<>());
	database.get(dataset).put(wordRank.getWord(), wordRank);
	return database.get(dataset).get(wordRank.getWord());
    }

    public void saveAll(String dataset, List<WordRank> wordRanks) {
	database.putIfAbsent(dataset, new HashMap<>());
	wordRanks.forEach(w -> database.get(dataset).put(w.getWord(), w));

    }

    public long count(String dataset) {
	return database.get(dataset).size();
    }

    public Set<String> getDatasetNames() {
	return database.keySet();
    }
}
