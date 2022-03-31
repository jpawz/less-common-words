package com.example.service;

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

}
