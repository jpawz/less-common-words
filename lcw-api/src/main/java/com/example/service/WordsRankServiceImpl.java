package com.example.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	public List<WordRank> getWordsRankForText(String text) {

		List<String> words = getWords(text);
		List<WordRank> wordRanks = new ArrayList<>(wordRankRepository.findByWordIn(words));

		words.forEach(s -> {
			WordRank wordRank = new WordRank();
			wordRank.setWord(s.toLowerCase());
			if (!wordRanks.contains(wordRank)) {
				wordRank.setRank(WordRank.RANK_NEW);
				wordRanks.add(wordRank);
			}
		});

		return wordRanks;
	}

	protected static List<String> getWords(String text) {
		String splitRegEx = "\\W+";
		List<String> words = Arrays.asList(text.split(splitRegEx));
		return words;
	}

}
