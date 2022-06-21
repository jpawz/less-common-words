package com.example.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.WordRank;
import com.example.repository.WordRankRepository;

@Service
public class WordsRankService {

    private final WordRankRepository wordRankRepository;

    @Autowired
    public WordsRankService(WordRankRepository repository) {
	this.wordRankRepository = repository;
    }

    /**
     * Store a custom words rank values in DB.
     * 
     * @param wordsRank - WordRank objects.
     * @return newly created WordRank
     */
    public WordRank saveWordsRank(WordRank wordsRank) {
	return wordRankRepository.save(wordsRank);
    }

    public WordRank getWordRankForWord(String word) {
	return this.wordRankRepository.findByWord(word);
    }

    public List<WordRank> getWordsRankForWords(Set<String> words) {
	return wordRankRepository.findByWordIn(words);
    }

    public List<WordRank> getWordsRankForWordsWhereRankIsGreaterThan(Set<String> words, int limit) {
	return wordRankRepository.findByWordInAndRankGreaterThan(words, limit);
    }
}
