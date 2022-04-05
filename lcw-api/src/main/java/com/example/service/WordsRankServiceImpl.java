package com.example.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.WordRank;
import com.example.repository.WordRankRepository;

@Service
public class WordsRankServiceImpl implements WordsRankService {

	private final WordRankRepository wordRankRepository;

	@Autowired
	public WordsRankServiceImpl(WordRankRepository repository) {
		this.wordRankRepository = repository;
	}

	@Override
	public WordRank saveWordsRank(WordRank wordsRank) {
		return wordRankRepository.save(wordsRank);
	}

	@Override
	public WordRank getWordRankForWord(String word) {
		return this.wordRankRepository.findByWord(word);
	}

	@Override
	public List<WordRank> getWordsRankForWords(Set<String> words) {
		return wordRankRepository.findByWordIn(words);
	}

}
